package payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.ServiceImpl;

@WebServlet("/cancelOrder")
public class CancelOrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = req.getParameter("cloth_num");
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		if (parameter != null && userId != null) {
			int cloth_num = Integer.parseInt(parameter);
			boolean cancelOrder = ServiceImpl.getInstance().cancelOrder(cloth_num, userId);
			PrintWriter writer = resp.getWriter();
			if (cancelOrder) {
				writer.print("성공");
			} else {
				writer.print("실패");
			}
		}
	}
}
