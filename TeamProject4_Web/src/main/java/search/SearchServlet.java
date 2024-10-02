package search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import image.Image;
import main.ServiceImpl;
import material.AppContextListener;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	AppContextListener app;
	ServiceImpl serviceImpl = ServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getAttribute("봄") == null) {
			List<Image> allImage = serviceImpl.findAllImage();
			HttpSession session = req.getSession();

			for (Image image : allImage) {
				if (image.getImg_num() == 5) {
					session.setAttribute("봄", image.getImg_64());
					System.out.println("들어감5");
				} else if (image.getImg_num() == 6) {
					session.setAttribute("여름", image.getImg_64());
					System.out.println("들어감6");
				} else if (image.getImg_num() == 7) {
					session.setAttribute("가을", image.getImg_64());
					System.out.println("들어감7");
				} else if (image.getImg_num() == 8) {
					session.setAttribute("겨울", image.getImg_64());
					System.out.println("들어감8");
				}
			}
		}

		req.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String season = req.getParameter("season");
		int parsedSeason = Integer.valueOf(season);
		System.out.println(parsedSeason);
	}
}
