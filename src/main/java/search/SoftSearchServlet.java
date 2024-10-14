package search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import image.ClothImage;
import image.Image;
import main.ServiceImpl;
import material.Cloth;
import material.ClothSorter;

@WebServlet("/softSearch")
public class SoftSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/SoftSearch.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userInput = req.getParameter("userInput");
		String sortOption = req.getParameter("sortOption");
		
		
		SoftSearchAlgorithm algorithm = new SoftSearchAlgorithm();
		List<Cloth> searchCloth = algorithm.searchCloth(userInput, null);
		HttpSession session = req.getSession();

		session.setAttribute("userInputDetail", userInput);
		
		if (sortOption != null) {
			if (sortOption.equals("lowPrice")) {
				ClothSorter.getInstance().sortLowPrice(searchCloth);
			} else if (sortOption.equals("highPrice")) {
				ClothSorter.getInstance().sortHighPrice(searchCloth);
			} else if (sortOption.equals("name")) {
				ClothSorter.getInstance().sortByName(searchCloth);
			}
			session.setAttribute("sortOption", sortOption);
		} else {
			session.removeAttribute("sortOption");
		}
	
		session.setAttribute("searchCloth", searchCloth);
	
		resp.sendRedirect("/softSearch");
	}

}
