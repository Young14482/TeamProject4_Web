package clothdetail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

import main.ServiceImpl;
import material.Cloth;
import material.DataManager;

@WebServlet("/detailPage")
public class ClothDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String clothNumStr = (String) req.getParameter("clothNum");
			Integer clothNum = Integer.parseInt(clothNumStr);
			List<Cloth> allCloth = (List<Cloth>) DataManager.getData("allCloth");

			Cloth chooseCloth = null;
			for (Cloth cloth : allCloth) {
				if (cloth.getCloth_num() == clothNum) {
					chooseCloth = cloth;
				}
			}
			if (chooseCloth == null) {
				resp.sendRedirect("/TeamProject4_Web/main");
			} else {
				
				List<Review> reviewList = ReviewService.getInstance().findReview(chooseCloth.getCloth_num());
				HttpSession session = req.getSession();
				resp.setCharacterEncoding("UTF-8");
				session.setAttribute("chooseCloth", chooseCloth);
				session.setAttribute("reviewList", reviewList);
				req.getRequestDispatcher("/WEB-INF/views/detailPage.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			resp.sendRedirect("/main");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
