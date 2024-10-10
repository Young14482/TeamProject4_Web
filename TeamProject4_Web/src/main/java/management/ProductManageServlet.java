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

@WebServlet("/productManage")
public class ProductManageServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		List<Cloth> allClothList = null; // 모든 옷 리스트

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			allClothList = manageMapper.getAllCloth();

			// 전체 상품 수
			int allClothCount = manageMapper.getAllClothCount();
			req.setAttribute("allClothCount", allClothCount);
		}

		req.setAttribute("allClothList", allClothList);

		req.getRequestDispatcher("/WEB-INF/views/productManage.jsp").forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 삭제할 상품의 cloth_num 가져오기
		String clothNumParam = req.getParameter("cloth_num");

		if (clothNumParam != null && !clothNumParam.isEmpty()) {
			int clothNum = Integer.parseInt(clothNumParam);

			// SqlSession을 이용하여 데이터베이스에서 해당 상품 삭제
			try (SqlSession sqlSession = app.getSqlSession()) {
				ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
				manageMapper.deleteCloth(clothNum); // cloth_num으로 해당 상품 삭제
				sqlSession.commit(); // 변경사항을 DB에 반영
			}

			// 삭제 성공 시 상태 코드 204 (No Content) 반환
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			
		} else {
			// cloth_num이 없거나 잘못된 경우 400 Bad Request 반환
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid cloth_num");
		}
	}

//	@Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        app = new AppContextListener();
//        
//        // 삭제할 상품의 cloth_num 가져오기
//        String clothNumParam = req.getParameter("cloth_num");
//        
//        if (clothNumParam != null && !clothNumParam.isEmpty()) {
//            int clothNum = Integer.parseInt(clothNumParam);
//            
//            // SqlSession을 이용하여 데이터베이스에서 해당 상품 삭제
//            try (SqlSession sqlSession = app.getSqlSession()) {
//                ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
//                manageMapper.delete(clothNum); // cloth_num으로 해당 상품 삭제
//                sqlSession.commit(); // 변경사항을 DB에 반영
//            }
//        }
//
//        // 삭제 후 상품 목록 페이지로 리다이렉트
//        resp.sendRedirect("productManage");
//    }

}