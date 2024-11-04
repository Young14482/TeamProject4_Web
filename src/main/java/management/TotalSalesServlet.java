package management;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/totalSales")
public class TotalSalesServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 userId가 admin이어야 관리 페이지 진입 가능
		if (userId.equals("admin")) {
			app = new AppContextListener();

			List<JoinUser> salesHistoryList = null; // 판매 내역 리스트
			Map<String, Integer> salesGroupedByDate = new LinkedHashMap<>();

			try (SqlSession sqlSession = app.getSqlSession()) {
				ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

				salesHistoryList = manageMapper.getSalesHistory();

				// 총 매출 금액
				int totalSales = manageMapper.getAllSales();
				req.setAttribute("totalSales", totalSales);

				// 총 판매 건 수
				int salesHistoryCount = manageMapper.getCountAllSales();
				req.setAttribute("salesHistoryCount", salesHistoryCount);

				// salesHistoryList에서 각 판매 데이터의 날짜를 기준으로 그룹화
				for (JoinUser joinUser : salesHistoryList) {
					// 결제일을 "yyyy.MM.dd" 형식으로 포맷
					String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(joinUser.getPayment_date());

					// 이미 해당 날짜가 맵에 있으면 현재 매출액을 더함
					salesGroupedByDate.put(formattedDate, salesGroupedByDate.getOrDefault(formattedDate, 0)
							+ joinUser.getCloth_price() * joinUser.getPayment_count());
				}
			}

			req.setAttribute("salesHistoryList", salesHistoryList);
			req.setAttribute("salesGroupedByDate", salesGroupedByDate);

			req.getRequestDispatcher("/WEB-INF/views/totalSales.jsp").forward(req, resp);

		} else { // 로그인한 userId가 admin이 아닐 경우 메인 페이지만 진입 가능
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}
}