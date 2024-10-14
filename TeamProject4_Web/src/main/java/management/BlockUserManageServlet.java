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

@WebServlet("/blockUserManage")
public class BlockUserManageServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 userId가 admin이어야 관리 페이지 진입 가능
		if (userId.equals("admin")) {
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

		} else { // 로그인한 userId가 admin이 아닐 경우 메인 페이지만 진입 가능
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}
}