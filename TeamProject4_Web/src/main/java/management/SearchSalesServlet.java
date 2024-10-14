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

@WebServlet("/searchSales")
public class SearchSalesServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

		// 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchSalesField"); // 검색 조건 받아오기
		String searchText = req.getParameter("searchSalesText"); // 입력한 검색어 받아오기

		List<JoinUser> searchSalesList = null; // 판매 내역 검색 결과 리스트
		Integer totalSalesByOption = null; // 조건 별 매출액 저장
		int countByOption = 0; // 조건 별 판매 건 수 저장

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			if ("salesDate".equals(searchField)) {
				// 결제 일시 별 검색된 판매 내역 리스트
				searchSalesList = manageMapper.getSelectSalesByDate(searchText);
				// 결제 일시 별 매출액
				totalSalesByOption = manageMapper.getTotalSalesByDate(searchText);
				// 결제 일시 별 판매 건수
				countByOption = manageMapper.getCountSalesByDate(searchText);

			} else if ("salesUserId".equals(searchField)) {
				// 회원 아이디 별 검색된 판매 내역 리스트
				searchSalesList = manageMapper.getSelectSalesById(searchText);
				// 회원 아이디 별 매출액
				totalSalesByOption = manageMapper.getTotalSalesById(searchText);
				// 회원 아이디 별 판매 건 수
				countByOption = manageMapper.getCountSalesById(searchText);

			} else if ("salesClothName".equals(searchField)) {
				// 상품명 별 검색된 판매 내역 리스트
				searchSalesList = manageMapper.getSelectSalesByClothName(searchText);
				// 상품명 별 매출액
				totalSalesByOption = manageMapper.getTotalSalesByClothName(searchText);
				// 상품명 별 판매 건 수
				countByOption = manageMapper.getCountSalesByClothName(searchText);

			} else if ("salesClothBrand".equals(searchField)) {
				// 상품 브랜드 별 검색된 판매 내역 리스트
				searchSalesList = manageMapper.getSelectSalesByBrand(searchText);
				// 상품 브랜드 별 매출액
				totalSalesByOption = manageMapper.getTotalSalesByBrand(searchText);
				// 상품 브랜드 별 판매 건 수
				countByOption = manageMapper.getCountSalesByBrand(searchText);
			}
		}

		req.setAttribute("salesHistoryList", searchSalesList);
		req.setAttribute("totalSales", totalSalesByOption);
		req.setAttribute("salesHistoryCount", countByOption);

		req.getRequestDispatcher("/WEB-INF/views/totalSales.jsp").forward(req, resp);

		System.out.println(searchSalesList);
	}
}