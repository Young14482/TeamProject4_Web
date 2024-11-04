package management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/searchPurchase")
public class SearchPurchaseServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

		// 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchPurchaseField"); // 검색 조건 받아오기
		String searchText = req.getParameter("searchPurchaseText"); // 입력한 검색어 받아오기

		List<JoinUser> searchPurchaseList = null; // 소비자 구매 현황 검색 결과 리스트
		int CountPurchaseUser = 0; // 구매 회원 수 

		// 가격 범위 검색을 위한 변수
		String minPurchasePriceString = req.getParameter("minPurchasePrice");
		String maxPurchasePriceString = req.getParameter("maxPurchasePrice");

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			if ("purchaseUserId".equals(searchField)) {
				// 회원 아이디 별 검색된 구매 현황 리스트
				searchPurchaseList = manageMapper.getSelectPurchaseById(searchText);
				// 구매 회원 수 조회
				CountPurchaseUser = manageMapper.getCountPurchaseById(searchText);

			} else if ("purchaseUserName".equals(searchField)) {
				// 회원 이름 별 검색된 구매 현황 리스트
				searchPurchaseList = manageMapper.getSelectPurchaseByName(searchText);
				// 회원 이름 별 구매 회원 수 조회
				CountPurchaseUser = manageMapper.getCountPurchaseByName(searchText);

			} else if (req.getParameter("minPurchasePrice") != null && req.getParameter("maxPurchasePrice") != null) {
				// 가격 범위 검색
				if (minPurchasePriceString.isEmpty() && !maxPurchasePriceString.isEmpty()) {
					req.setAttribute("errorMessage", "최소 금액을 선택해 주세요.");

					// 다시 요청을 보낼 페이지로 포워딩
					req.getRequestDispatcher("/WEB-INF/views/purchaseStatus.jsp").forward(req, resp);
					return; // 추가 처리를 막음

				} else if (!minPurchasePriceString.isEmpty() && maxPurchasePriceString.isEmpty()) {
					req.setAttribute("errorMessage", "최대 금액을 선택해 주세요.");

					// 다시 요청을 보낼 페이지로 포워딩
					req.getRequestDispatcher("/WEB-INF/views/purchaseStatus.jsp").forward(req, resp);
					return; // 추가 처리를 막음
				}
				
				int minPurchasePrice = Integer.parseInt(minPurchasePriceString);
                int maxPurchasePrice = Integer.parseInt(maxPurchasePriceString);

				// 가격 범위로 검색된 구매 현황 리스트
				searchPurchaseList = manageMapper.getSelectPurchaseByPriceRange(minPurchasePrice, maxPurchasePrice);
				// 가격 범위 별 구매 회원 수 조회
				CountPurchaseUser = manageMapper.getCountPurchaseByPriceRange(minPurchasePrice, maxPurchasePrice);
			}
		}

		req.setAttribute("purchaseStatusList", searchPurchaseList);
		req.setAttribute("purchaseUserCount", CountPurchaseUser);

		req.getRequestDispatcher("/WEB-INF/views/purchaseStatus.jsp").forward(req, resp);

		System.out.println(searchPurchaseList);
	}
}