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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

			session.setAttribute("userId", "nana1234");
			String userId = (String) session.getAttribute("userId");
			List<ShoppingCartItem> shoppingCartList = serviceImpl.selectShoppingCart(userId);

			session.setAttribute("shoppingCartList", shoppingCartList);

			req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 요청 인코딩 설정
		resp.setCharacterEncoding("UTF-8"); // 응답 인코딩 설정

		// JSON 데이터를 읽어서 String으로 변환
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		HttpSession session = req.getSession();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String body = sb.toString(); // StringBuilder를 String 형태로 변환

		// JSON 데이터를 ShoppingCartItem 리스트로 변환
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ShoppingCartItem> orderList = mapper.readValue(body, new TypeReference<List<ShoppingCartItem>>() {
			});
			int result = 0;
			// 주문 처리 로직 추가
			session.setAttribute("orderList", orderList); // 장바구니
			for (ShoppingCartItem order : orderList) {
				result += serviceImpl.insertPayment(order);
			}
			if (result != 0) {
				result = 0;
				for (ShoppingCartItem order : orderList) {
				result += serviceImpl.deleteFromShoppingCart(order.getCloth_num());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 응답 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"status\": \"success\"}");
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
