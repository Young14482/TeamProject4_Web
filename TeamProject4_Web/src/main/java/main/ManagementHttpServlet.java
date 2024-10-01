package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/management")
public class ManagementHttpServlet extends HttpServlet {
	AppContextListener app;
	ServiceImpl serviceImpl = ServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.getRequestDispatcher("/WEB-INF/views/management.jsp").forward(req, resp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}