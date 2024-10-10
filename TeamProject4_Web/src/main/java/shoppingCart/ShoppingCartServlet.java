package shoppingCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.ServiceImpl;
import material.AppContextListener;
import material.Cloth;
//TODO : 현재 userId 직접 호출중임 >> 세션으로 받아와야함, jsp 버튼들 구현해야함

@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	AppContextListener app;
	ServiceImpl serviceImpl = ServiceImpl.getInstance();
	List<Cloth> clothList = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		app.contextInitialized(null);
		try {
			HttpSession session = req.getSession();
//			String userId = (String) session.getAttribute("userId");
			String userId = "nana1234";

			List<ShoppingCartItem> shoppingCartList = serviceImpl.selectShoppingCart(userId);

			session.setAttribute("shoppingCartList", shoppingCartList);

			req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    BufferedReader reader = req.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    
	    String body = sb.toString();
	    String[] selectedItems = body.split("=")[1].split(",");
	    
	    if (selectedItems != null && selectedItems.length > 0) {
	        for (String clothNum : selectedItems) {
	            serviceImpl.deleteFromShoppingCart(Integer.parseInt(clothNum));
	        }
	    }
	    
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write("{\"status\": \"success\"}");
	}

}
