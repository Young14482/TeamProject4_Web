package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/user/pw")
@Slf4j
public class UserPwAPI extends HttpServlet {
	UserService service = new UserServiceImple();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String json = sb.toString();
		User user = mapper.readValue(json, User.class);

		if (user.getPw() == null) {
			String pw = service.UserPw(user.getName(), user.getId());

			// 정해진게 있다 .. 표준 > 아닌헤더도 쓸순있다.
			if (pw != null) {
				resp.setCharacterEncoding("utf-8");
				resp.setHeader("Content-Type", "text/plain; charset=utf-8");
			} else {
				resp.setStatus(416);
			}
		} else if (userId != null) {
			user.setId(userId);
			int pw = service.userChangePw2(user.getId(), user.getPw());
			System.out.println(pw);
			if (pw > 0) {
				resp.setCharacterEncoding("utf-8");
				resp.setHeader("Content-Type", "text/plain; charset=utf-8");
				resp.setStatus(200);
				
			} else {
				resp.setStatus(414);
			}
		} else {
			int pw = service.userChangePw(user.getName(), user.getId(), user.getPw());
			if (pw > 0) {
				resp.setCharacterEncoding("utf-8");
				resp.setHeader("Content-Type", "text/plain; charset=utf-8");
				resp.setStatus(200);
				req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
			} else {
				resp.setStatus(414);
			}
		}
	}
}
