package payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import clothdetail.Review;
import main.ServiceImpl;

@WebServlet("/userPayment")
public class PaymentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		List<PayCloth> payClothList =  ServiceImpl.getInstance().findUserClothsToPay(userId);
		session.setAttribute("payClothList", payClothList);
		req.getRequestDispatcher("/WEB-INF/views/userPayment.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    req.setCharacterEncoding("utf-8");
	    // JSON 데이터를 읽기 위해 BufferedReader 사용
	    BufferedReader reader = req.getReader();
	    StringBuilder jsonBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonBuilder.append(line);
	    }
	    String json = jsonBuilder.toString();

	    // JSON 데이터를 객체로 변환
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        Review review = mapper.readValue(json, Review.class);
	        ServiceImpl.getInstance().insertReview(review);
	       
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        resp.getWriter().write("Invalid JSON format");
	    }
	}

	
	
	
}
