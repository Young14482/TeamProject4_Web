package search;

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

			List<String> attNameList = Arrays.asList("봄", "여름", "가을", "겨울", "빨강", "노랑", "파랑", "초록", "갈색", "무채색");

			for (int i = 0; i < attNameList.size(); i++) {
				for (Image image : allImage) {
					if (image.getImg_num() == i + 5) {
						session.setAttribute(attNameList.get(i), image.getImg_64());
						break;
					} 
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
