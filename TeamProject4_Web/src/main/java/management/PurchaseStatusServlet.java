package management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/purchaseStatus")
public class PurchaseStatusServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 userId가 admin이어야 관리 페이지 진입 가능
		if (userId.equals("admin")) {
			app = new AppContextListener();

			List<JoinUser> purchaseStatusList = null; // 소비자 구매 현황 리스트

			try (SqlSession sqlSession = app.getSqlSession()) {
				ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

				purchaseStatusList = manageMapper.getPurchaseStatus();

				// 구매 회원 수
				int countPurchase = manageMapper.getCountPurchase();
				req.setAttribute("purchaseUserCount", countPurchase);
			}

			req.setAttribute("purchaseStatusList", purchaseStatusList);

			req.getRequestDispatcher("/WEB-INF/views/purchaseStatus.jsp").forward(req, resp);

		} else { // 로그인한 userId가 admin이 아닐 경우 메인 페이지만 진입 가능
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}
}