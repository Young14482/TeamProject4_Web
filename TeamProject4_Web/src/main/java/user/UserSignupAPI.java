package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@WebServlet("/signup")
@Slf4j
public class UserSignupAPI extends HttpServlet {
	private UserService service = UserServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/Signup.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		if (user.getName() != null) {
			int result = service.InsertUser(user);
			resp.setCharacterEncoding("utf-8");

			resp.setHeader("Content-Type", "application/json; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			if (result > 0) {
				pw.print(json); // body
				pw.flush(); // 버퍼 비우는거
			}

		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		int count = service.userIdCheck(user.getId());

		if (count > 0) {
			resp.setStatus(200);
		} else {
			resp.setStatus(414);
		}

	}

}
