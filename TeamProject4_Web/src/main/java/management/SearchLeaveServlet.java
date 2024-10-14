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

@WebServlet("/searchLeaveUser")
public class SearchLeaveServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

		// 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchLeaveField"); // 검색 조건 받아오기
		String searchText = req.getParameter("searchLeaveText"); // 입력한 검색어 받아오기
		
		List<JoinUser> searchLeaveList = null; // 탈퇴 회원 검색 결과 리스트
		int searchLeaveCount = 0; // 조건 별로 검색함에 따라 달라질 탈퇴 회원 수
		
		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			if ("leaveUserId".equals(searchField)) {
				// 회원 아이디 별 검색된 탈퇴 회원 리스트
				searchLeaveList = manageMapper.getSelectLeaveById(searchText);
				// 회원 아이디 별 검색된 탈퇴 회원 수
				searchLeaveCount = manageMapper.getCountLeaveById(searchText);
				
			} else if ("leaveUserName".equals(searchField)) {
				// 회원 이름 별 검색된 탈퇴 회원 리스트
				searchLeaveList = manageMapper.getSelectLeaveByName(searchText);
				
				// 회원 이름 별 검색된 탈퇴 회원 수
				searchLeaveCount = manageMapper.getCountLeaveByName(searchText);
			}
		}
		
		req.setAttribute("leaveList", searchLeaveList);
	    req.setAttribute("leaveUserCount", searchLeaveCount);
	    
	    req.getRequestDispatcher("/WEB-INF/views/leaveUserManage.jsp").forward(req, resp);
	    
	    System.out.println(searchLeaveList);
	}
}