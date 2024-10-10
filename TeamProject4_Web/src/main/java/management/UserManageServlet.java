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

import main.ServiceImpl;
import material.AppContextListener;

// 작성자 : 이나겸
// /userManage/search? => 검색용 서블릿 하나 더 만드는 경우에 쓸 URL / ?에는 파라미터값을 받는다는 조건 하에 쓰는것
@WebServlet("/userManage")
public class UserManageServlet extends HttpServlet {
	AppContextListener app;
	ManageService manageService = ManageServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		List<JoinUser> userList = null; // 가입 회원 리스트

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			userList = manageMapper.getJoinUser();

			// 가입 회원 수
			int joinUserCount = manageMapper.getJoinUserCount();
			req.setAttribute("joinUserCount", joinUserCount);
		}

		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/WEB-INF/views/userManage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();

		String userId = req.getParameter("userId"); // 요청에서 userId 받아오기

		try (SqlSession sqlSession = app.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			// 배송지를 여러 개 갖고있는 해당 회원 별 모든 배송지 리스트
			List<String> deliveryAddresses = manageMapper.getAllDeliveryAddress(userId);

			// JSON으로 변환하여 클라이언트로 반환
			// Gson 라이브러리 : Java 객체를 JSON 형식으로 변환하거나 JSON 데이터를 Java 객체로 변환할 수 있음
			Gson gson = new Gson();
			String jsonDeliveryAddress = gson.toJson(deliveryAddresses);

			resp.setHeader("Content-Type", "application/json; charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jsonDeliveryAddress);
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
		String action = jsonObject.get("action").getAsString(); // action을 JSON에서 가져오기
		JoinUser joinUser = gson.fromJson(jsonData.toString(), JoinUser.class); // JoinUser 객체 생성

		try (SqlSession sqlSession = app.getSqlSession()) {
			
			if ("changeGrade".equals(action)) { // 회원 등급 수정
				
				JoinUser updatedGrade = manageService.updateUserGrade(joinUser);
				
				if (updatedGrade != null) { // 회원 등급 수정 성공했을 경우
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().write("회원 등급이 성공적으로 수정되었습니다.");
					
				} else { // 회원 등급 수정 실패했을 경우
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					resp.getWriter().write("회원 등급 수정이 실패하였습니다.");
				}
				
			} else if ("blockUser".equals(action)) { // 회원 차단
				
				JoinUser blockedUser = manageService.updateUserBlock(joinUser);
				
				if (blockedUser != null) { // 회원 차단 성공했을 경우
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().write("회원이 차단되었습니다.");
					
				} else { // 회원 차단 실패했을 경우
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					resp.getWriter().write("회원 차단이 실패하였습니다.");
				}
			}
			
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("서버 오류가 발생했습니다.");
			
			e.printStackTrace();
		}
	}
}