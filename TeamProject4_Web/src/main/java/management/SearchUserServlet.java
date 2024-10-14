package management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

	    // 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchField"); // 검색 조건 받아오기
	    String searchText = req.getParameter("searchText"); // 입력한 검색어 받아오기

	    List<JoinUser> searchUserList = null; // 회원 검색 결과 리스트
	    int searchUserCount = 0; // 조건 별로 회원을 검색함에 따라 달라질 회원 수 변수

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper managerMapper = sqlSession.getMapper(ManageMapper.class);

			if ("userId".equals(searchField)) { // 아이디로 회원 검색
				// 회원 아이디 별 검색된 회원 리스트 (무조건 한 사람만 들어가야함)
				searchUserList = managerMapper.getSelectById(searchText);
				// 회원 아이디 검색된 별 회원 수 (무조건 1이 나와야함)
				searchUserCount = managerMapper.getCountById(searchText);

			} else if ("userName".equals(searchField)) {
				// 회원명 별 검색된 회원 리스트
				searchUserList = managerMapper.getSelectByName(searchText);
				// 회원명 별 검색된 회원 수
				searchUserCount = managerMapper.getCountByName(searchText);
				
			} else if ("userGrade".equals(searchField)) { // 회원 등급으로 회원 검색
				// 회원 등급 별 검색된 회원 리스트
				searchUserList = managerMapper.getSelectByGrade(searchText);
				// 회원 등급 별 검색된 회원 수
				searchUserCount = managerMapper.getCountByGrade(searchText);

			} else if ("userGender".equals(searchField)) { // 성별로 회원 검색
				// 성별 별 검색된 회원 리스트
				searchUserList = managerMapper.getSelectByGender(searchText);
				// 성별 별 검색된 회원 수
				searchUserCount = managerMapper.getCountByGender(searchText);
			}
		}

	    req.setAttribute("userList", searchUserList);
	    req.setAttribute("joinUserCount", searchUserCount);
	    
	    req.getRequestDispatcher("/WEB-INF/views/userManage.jsp").forward(req, resp);
	    
		System.out.println(searchUserList);
	}
}