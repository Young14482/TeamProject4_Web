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

@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

		// 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchProductField"); // 검색 조건 받아오기
		String searchText = req.getParameter("searchProductText"); // 입력한 검색어 받아오기
		
		List<Cloth> searchProductList = null; // 상품 검색 결과 리스트
		int CountProduct = 0; // 조건 별로 상품을 검색함에 따라 달라질 상품 수
		
		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			if ("productName".equals(searchField)) {
				// 상품명 별 검색된 상품 리스트
				searchProductList = manageMapper.getClothByName(searchText);
				
				// 상품명 별 검색된 상품 수
				CountProduct = manageMapper.getCountClothByName(searchText);

			} else if ("productBrand".equals(searchField)) {
				// 브랜드 별 검색된 상품 리스트
				searchProductList = manageMapper.getClothByBrand(searchText);

				// 브랜드 별 검색된 상품 수
				CountProduct = manageMapper.getCountClothByBrand(searchText);
			}
		}
		
		req.setAttribute("allClothList", searchProductList);
		req.setAttribute("allClothCount", CountProduct);

		req.getRequestDispatcher("/WEB-INF/views/productManage.jsp").forward(req, resp);

		System.out.println(searchProductList);
	}
}