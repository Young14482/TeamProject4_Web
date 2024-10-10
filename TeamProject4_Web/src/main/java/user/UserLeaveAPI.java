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

@WebServlet("/userLeave")
@Slf4j
public class UserLeaveAPI extends HttpServlet {
	UserService service = new UserServiceImple();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String json = sb.toString();
		User user = mapper.readValue(json, User.class);

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		String pw = user.getPw();
		String Pw = service.Pw(userId);
		if (Pw != null) {
			if (Pw.equals(pw)) {
				resp.setStatus(200);
			} else {
				resp.setStatus(403);
			}
		} else {
			resp.setStatus(414);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		
		String userId = (String) session.getAttribute("userId");
		
		int num = service.userLeave(userId);
		if(num > 0) {
			session.removeAttribute("userId"); // 특정 속성 제거
			resp.setStatus(200);
		}else {
			resp.setStatus(419);
		}
		
	}
}
