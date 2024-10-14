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

@WebServlet("/leaveUserManage")
public class LeaveUserManageServlet extends HttpServlet {
	AppContextListener app;
	ManageServiceImpl manageServiceImpl = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		List<JoinUser> leaveList = null; // 탈퇴 회원 리스트

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			leaveList = manageMapper.getLeaveUser();

			// 탈퇴 회원 수
			int leaveUserCount = manageMapper.getLeaveUserCount();
			req.setAttribute("leaveUserCount", leaveUserCount);
		}
		
		req.setAttribute("leaveList", leaveList);
		
		req.getRequestDispatcher("/WEB-INF/views/leaveUserManage.jsp").forward(req, resp);
	}
}