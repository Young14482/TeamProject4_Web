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

@WebServlet("/blockUserManage")
public class BlockUserManageServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		List<JoinUser> blackList = null; // 차단 회원 리스트
		
		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			blackList = manageMapper.getBlockUser();
			
			// 차단 회원 수
			int blockUserCount = manageMapper.getBlockUserCount();
			req.setAttribute("blockUserCount", blockUserCount);
			
		}
		
		req.setAttribute("blackList", blackList);
		
		req.getRequestDispatcher("/WEB-INF/views/blockUserManage.jsp").forward(req, resp);
	}
}