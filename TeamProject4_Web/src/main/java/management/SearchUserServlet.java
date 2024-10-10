package management;

import java.io.IOException;
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

@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {
	AppContextListener app;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		// 값이 한글일 경우를 대비해서 encode
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		
		// 요청에서 검색 필드와 검색어 받아오기
		String searchField = req.getParameter("searchField");
		String searchText = req.getParameter("searchText");
		
		List<JoinUser> searchResult = null; // 검색 결과 리스트
		
		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper managerMapper = sqlSession.getMapper(ManageMapper.class);
			
			if ("userId".equals(searchField)) { // 아이디로 회원 검색
				searchResult = managerMapper.getSelectById(searchText);
				
			} else if ("userGrade".equals(searchField)) { // 회원 등급으로 회원 검색
				searchResult = managerMapper.getSelectByGrade(searchText);
				
			} else if ("userGender".equals(searchField)) { // 성별로 회원 검색
				searchResult = managerMapper.getSelectByGender(searchText);
			}
		}
	}
}