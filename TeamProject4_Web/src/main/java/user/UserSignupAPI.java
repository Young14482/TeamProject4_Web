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
public class UserSignupAPI extends HttpServlet{
	private UserService service = UserServiceImple.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/Signup.jsp")
		.forward(req, resp);
//		req.getRequestDispatcher("/WEB-INF/views/kakao.jsp")
//		.forward(req, resp);
		
//		String srant_type = "authorization_code";
//		String client_id = "8f876a5cadf6154cf159eff29dd33777";
//		String redirect_uri = "http://localhost:8080/signup";
//		String code = req.getParameter("code"); // 인가 코드
//		if(code == null) {
//			code = "FF";
//		}
//		 OkHttpClient client = new OkHttpClient();
//	        // Form 데이터 생성
//	        RequestBody formBody = new FormBody.Builder()
//	                .add("grant_type", srant_type)
//	                .add("client_id", client_id)
//	                .add("redirect_uri", redirect_uri)
//	                .add("code", code)
//	                .build();
//	        // 요청 생성
//	        Request request = new Request.Builder()
//	                .url("https://kauth.kakao.com/oauth/token") // 실제 요청 URL로 변경하세요
//	                .post(formBody)
//	                .build();
//	        // 요청 보내기 및 응답 처리
//	        try (Response response = client.newCall(request).execute()) {
//	            if (response.isSuccessful()) {
//	            	String responseBody = response.body().string();
//	                // JSON 파싱
//	                JSONObject jsonObject = new JSONObject(responseBody);
//	                String accessToken = jsonObject.getString("access_token");
//	                System.out.println(accessToken);
//	            } else {
//	                System.out.println("Request failed: " + response.code());
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		
		
		
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
		
		int result = service.InsertUser(user);
		resp.setCharacterEncoding("utf-8");
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		if(result > 0) {
			pw.print(json); // body
			pw.flush(); // 버퍼 비우는거
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
	}
	
}
