package management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/searchPurchase")
public class SearchPurchaseServlet extends HttpServlet {
	AppContextListener app;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		
		// 값이 한글일 경우를 대비해 encoding
		req.setCharacterEncoding("utf-8");
		
	}
}