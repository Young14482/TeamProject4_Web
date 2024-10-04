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
import material.Cloth;

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

			List<String> attNameList2 = Arrays.asList("운동", "경사", "캠핑", "등산", "비즈니스", "일상", "실내");

			for (int i = 0; i < attNameList2.size(); i++) {
				for (Image image : allImage) {
					if (image.getImg_num() == i + 24) {
						session.setAttribute(attNameList2.get(i), image.getImg_64());
						break;
					}
				}
			}
		}
		req.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 확인용 출력중
		String season = req.getParameter("season");
		int parsedSeason = Integer.valueOf(season);
		

		String color = req.getParameter("color");
		int parsedColor = Integer.valueOf(color);
		

		String usage = req.getParameter("usage");
		int parsedUsage = Integer.valueOf(usage);
	

		String price = req.getParameter("price");
		String arr[] = price.split("~");
		String minPrice = arr[0];
		int parsedMinPrice = Integer.valueOf(minPrice);
		String maxPrice = arr[1];
		int parsedMaxPrice = Integer.valueOf(maxPrice);
		
		//List<Cloth> list = service.filter(parsedSeason, parsedColor, parsedUsage, parsedMinPrice, parsedMaxPrice);
		System.out.println("계절:" + parsedSeason);
		System.out.println("색:" + parsedColor);
		System.out.println("활동:" + parsedUsage);
		System.out.printf("최소값: %d, 최대값: %d\n", parsedMinPrice, parsedMaxPrice);
	}
}
