package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/user/Id")
@Slf4j
public class UserIdAPI extends HttpServlet {
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
		System.out.println(json);
		User user = mapper.readValue(json, User.class);

		String Id = service.UserId(user.getName(), user.getBirth());

		// 정해진게 있다 .. 표준 > 아닌헤더도 쓸순있다.
		if (Id != null) {
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Content-Type", "text/plain; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.print(Id); // body
			pw.flush(); // 버퍼 비우는거
		} else {
			resp.setStatus(416);
		}

	}
}