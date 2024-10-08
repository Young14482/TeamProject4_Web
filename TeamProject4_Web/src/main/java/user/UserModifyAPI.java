package user;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/modify")
@Slf4j
public class UserModifyAPI extends HttpServlet {
	UserService service = new UserServiceImple();

	@Override
	// 유저 정보 띄워주기
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String userId = (String) session.getAttribute("userId");
		// 여기서 동작그만
		User user = service.userModify(userId);
		System.out.println(user.toString());
		if (user != null) {
			String userName = user.getName();
			String userPhone = user.getPhone();
			String userAddress = user.getAddress();
			String userGrade = user.getGrade();

			req.setAttribute("userId", userId);
			req.setAttribute("userName", userName);
			req.setAttribute("userPhone", userPhone);
			req.setAttribute("userAddress", userAddress);
			req.setAttribute("userGrade", userGrade);

			req.getRequestDispatcher("/WEB-INF/views/UserModify.jsp").forward(req, resp);
		}
	}

	// 유저 정보 업데이트
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		
		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		String json = sb.toString();
		User user = mapper.readValue(json, User.class);
		user.setId(userId);
		System.out.println(user.toString());
		int num = service.userChangeModify(user.getName(), user.getPhone(), user.getAddress(), user.getId());
		System.out.println(num);
		if (num > 0) {
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Content-Type", "text/plain; charset=utf-8");
			resp.setStatus(200);
		} else {
			resp.setStatus(414);
		}

	}

}
