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

@WebServlet("/totalSales")
public class TotalSalesServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		List<JoinUser> salesHistoryList = null; // 판매 내역 리스트
		
		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			salesHistoryList = manageMapper.getSalesHistory();
			
			// 총 매출 금액
			int totalSales = manageMapper.getAllSales();
			req.setAttribute("totalSales", totalSales);
		}
		
		req.setAttribute("salesHistoryList", salesHistoryList);
		
		req.getRequestDispatcher("/WEB-INF/views/totalSales.jsp").forward(req, resp);
	}
}