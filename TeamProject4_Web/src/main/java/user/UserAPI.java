package user;

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
		req.getRequestDispatcher("/WEB-INF/views/Login.jsp")
		.forward(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");

		String Id = req.getParameter("Id");
		String Pw = req.getParameter("Pw");

		String UserPw = service.Pw(Id);
		
		if(UserPw != null) {
			
			if(UserPw.equals(Pw)) {
				
				HttpSession session = req.getSession();
				session.setAttribute("Id", Id);
				req.getRequestDispatcher("/WEB-INF/views/index.jsp")
					.forward(req, resp);
				
				System.out.println("로그인성공");
			} else {
				
				req.getRequestDispatcher("/WEB-INF/views/Login.jsp")
				.forward(req, resp);
				
				System.out.println("비밀번호가 올바르지 않음");
			}
		} else {
			
			req.getRequestDispatcher("/WEB-INF/views/Login.jsp")
			.forward(req, resp);
			
			System.out.println("회원가입이 필요함");
		}
		
	}
}
