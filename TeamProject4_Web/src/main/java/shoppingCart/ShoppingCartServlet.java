package shoppingCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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
import user.User;

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

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		Integer finalTotalPrice = null;
		try {
			Long longValue  = (Long) session.getAttribute("finalTotalPrice");
			finalTotalPrice = longValue.intValue();
		} catch (Exception e) {
			finalTotalPrice = (Integer) session.getAttribute("finalTotalPrice");
		}
		
		List<ShoppingCartItem> orderList = null;
		// JSON 데이터를 읽어서 String으로 변환
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String body = sb.toString();

		// 시작과 끝에 중괄호가 있는지 검사
		if (!body.startsWith("[")) {
			body = "[" + body;
		}
		if (!body.endsWith("]")) {
			body = body + "]";
		}
		// JSON 데이터를 ShoppingCartItem 리스트로 변환
		ObjectMapper mapper = new ObjectMapper();
		try {
			orderList = mapper.readValue(body, new TypeReference<List<ShoppingCartItem>>() {
			});
			int result = 0;
			// 주문 처리 로직 추가
			session.setAttribute("orderList", orderList); // 장바구니
			for (ShoppingCartItem order : orderList) {
				System.out.println(order.toString());
				String selectedSize = (String) session.getAttribute("selectedSize");
				
				if (selectedSize == null) {
					result += serviceImpl.insertPayment(order, 0);
				} else {
					result += serviceImpl.insertPayment(order, Integer.parseInt(selectedSize));
				}
				
			}
			if (result != 0) {
				result = 0;
				for (ShoppingCartItem order : orderList) {
					result += serviceImpl.deleteFromShoppingCart(order.getCloth_num());
					int clothNum = order.getCloth_num();
					int clothSold = serviceImpl.getClothSold(clothNum);
					int count = (order.getShoppingcart_count() + clothSold);
					serviceImpl.updateClothSold(clothNum, count);
				}
				int useMoney = serviceImpl.userUseMoney(userId);
				int totalPrice = useMoney + finalTotalPrice - 3500;
				serviceImpl.updateUseMoney(userId, totalPrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 응답 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"status\": \"success\"}");

//         int parsedCloth_num = Integer.parseInt(cloth_num);
//         
//         ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//         shoppingCartItem.setCloth_num(parsedCloth_num);
//         shoppingCartItem.setUser_Id((String) session.getAttribute("userId"));
//         shoppingCartItem.setShoppingcart_count(1);
//         
//         serviceImpl.insertPayment(shoppingCartItem);
//         
//         resp.sendRedirect("/userPayment");

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

	// 결제창에서 손놈 정보 json받아오는 용도의 doPut
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		User user = serviceImpl.getUserInfo(userId);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);

		// JSON 응답 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(userJson);
	}
	
	
}
