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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/leaveUserManage")
public class LeaveUserManageServlet extends HttpServlet {
	AppContextListener app;
	ManageService manageService = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 userId가 admin이어야 관리 페이지 진입 가능
		if (userId.equals("admin")) {
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

		} else { // 로그인한 userId가 admin이 아닐 경우 메인 페이지만 진입 가능
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 값이 한글일 경우를 대비해서 encode
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		// Request Body에서 JSON 데이터 읽기
		StringBuilder jsonData = new StringBuilder();
		String line;

		while ((line = req.getReader().readLine()) != null) {
			jsonData.append(line);
		}

		// JSON 데이터 parsing
		Gson gson = new Gson();
		// JsonObject로 읽어옴
		JsonObject jsonObject = gson.fromJson(jsonData.toString(), JsonObject.class);

		// 요청에서 action과 JoinUser 객체를 읽어오기
		String action = jsonObject.get("action").getAsString(); // action을 JSON에서 가져오기 (js 파일 참고)
		JoinUser joinUser = gson.fromJson(jsonData.toString(), JoinUser.class); // JoinUser 객체 생성

		try (SqlSession sqlSession = app.getSqlSession()) {

			if ("changeLeave".equals(action)) {

				JoinUser updatedUserLeave = manageService.updateUserLeave(joinUser);

				if (updatedUserLeave != null) { // 탈퇴 회원 계정 복구 성공했을 경우
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().write("회원 계정이 성공적으로 복구되었습니다.");

				} else { // 탈퇴 회원 계정 복구 실패했을 경우
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					resp.getWriter().write("회원 계정 복구를 실패하였습니다.");
				}
			}

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("서버 오류가 발생했습니다.");

			e.printStackTrace();
		}
	}
}