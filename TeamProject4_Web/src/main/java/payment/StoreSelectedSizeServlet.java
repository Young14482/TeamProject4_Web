package payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/storeSelectedSize")
public class StoreSelectedSizeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String selectedSize = req.getParameter("selectedSize");

        if (selectedSize == null || selectedSize.trim().isEmpty()) {
            System.out.println("빈 문자열이 전달됨");
        } else {
            
            if (selectedSize.equals("S")) {
            	selectedSize = "1";
            } else if (selectedSize.equals("M")) {
            	selectedSize = "2";
            } else if (selectedSize.equals("L")) {
            	selectedSize = "3";
            } else if (selectedSize.equals("XL")) {
            	selectedSize = "4";
            } else if (selectedSize.equals("XXL")) {
            	selectedSize = "5";
            }
        }

        session.setAttribute("selectedSize", selectedSize);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("세션에 사이즈 저장 완료");
    }
}

