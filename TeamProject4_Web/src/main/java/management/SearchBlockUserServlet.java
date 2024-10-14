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

@WebServlet("/searchBlockUser")
public class SearchBlockUserServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");

		// 요청으로 파라미터 값 받아오기
		String searchField = req.getParameter("searchBlockField"); // 검색 조건 받아오기
		String searchText = req.getParameter("searchBlockText"); // 입력한 검색어 받아오기

		List<JoinUser> searchBlackList = null; // 차단 회원 검색 결과 리스트
		int searchBlockUserCount = 0; // 조건 별로 차단 회원을 검색함에 따라 달라질 차단 회원 수 변수

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			if ("BlockUserId".equals(searchField)) {
				// 회원 아이디 별 검색된 차단 회원 리스트
				searchBlackList = manageMapper.getSelectBlockById(searchText);
				
				// 회원 아이디 별 검색된 차단 회원 수
				searchBlockUserCount = manageMapper.getCountBlockById(searchText);

			} else if ("BlockUserName".equals(searchField)) {
				// 회원 이름 별 검색된 차단 회원 리스트
				searchBlackList = manageMapper.getSelectBlockByName(searchText);

				// 회원 이름 별 검색된 차단 회원 수
				searchBlockUserCount = manageMapper.getCountBlockByName(searchText);
			}
		}

		req.setAttribute("blackList", searchBlackList);
		req.setAttribute("blockUserCount", searchBlockUserCount);

		req.getRequestDispatcher("/WEB-INF/views/blockUserManage.jsp").forward(req, resp);

		System.out.println(searchBlackList);
	}
}