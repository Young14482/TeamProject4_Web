package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/user")
@Slf4j
public class UserAPI extends HttpServlet {
	UserService service = new UserServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
	}

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
		if (user.getId() != null) {
			String id = user.getId();
			String pw = user.getPw();
			String Pw = service.Pw(id);
			if (Pw != null) {
				if (Pw.equals(pw)) {
					HttpSession session = req.getSession();
					session.setAttribute("userId", id);
				} else {
					resp.setStatus(403);
					System.out.println("비밀번호가 올바르지 않음");
				}
			} else {
				resp.setStatus(405);
				System.out.println("회원가입이 필요함");
			}
			
		} else if (user.getId() == null) {
			HttpSession session = req.getSession();
			String userId = (String) session.getAttribute("userId");
			String pw = user.getPw();
			String Pw = service.Pw(userId);
			if( Pw.equals(pw)) {
				resp.setStatus(200);
			} else {
				resp.setStatus(403);
			}
		}
	}

}
