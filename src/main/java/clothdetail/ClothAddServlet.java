package clothdetail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ServiceImpl;


@WebServlet("/add")
public class ClothAddServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String cloth_num = req.getParameter("cloth_num");
		
		if (userId != null && cloth_num != null) {
			ServiceImpl.getInstance().addToClothInShoppingCart(userId, Integer.parseInt(cloth_num));
			resp.sendRedirect("/shoppingCart");
		} else {
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
		
	}

}
