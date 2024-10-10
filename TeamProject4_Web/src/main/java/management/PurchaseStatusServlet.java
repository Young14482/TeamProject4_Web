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

@WebServlet("/purchaseStatus")
public class PurchaseStatusServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		List<JoinUser> purchaseStatusList = null; // 소비자 구매 현황 리스트
		
		try (SqlSession sqlSession = app.getSqlSession()){
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			purchaseStatusList = manageMapper.getPurchaseStatus();
		}
		
		req.setAttribute("purchaseStatusList", purchaseStatusList);
		
		req.getRequestDispatcher("/WEB-INF/views/purchaseStatus.jsp").forward(req, resp);
	}
}