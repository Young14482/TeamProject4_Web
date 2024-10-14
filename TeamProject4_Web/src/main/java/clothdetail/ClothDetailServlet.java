package clothdetail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.ServiceImpl;
import material.Cloth;
import material.DataManager;
import payment.ClothSize;

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
				resp.sendRedirect("/main");
			} else {
				ClothSize clothSize = ServiceImpl.getInstance().findClothSize(chooseCloth.getCloth_num());
				
				List<Review> reviewList = ReviewService.getInstance().findReview(chooseCloth.getCloth_num());
				HttpSession session = req.getSession();
				resp.setCharacterEncoding("UTF-8");
				session.setAttribute("chooseCloth", chooseCloth);
				session.setAttribute("reviewList", reviewList);
				session.setAttribute("maxSize", clothSize.getCloth_max_size());
				session.setAttribute("minSize", clothSize.getCloth_min_size());
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

