package main;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import image.Image;
import material.AppContextListener;
import material.Cloth;
import material.DataManager;

@WebServlet("/main")
public class MainHtmlServlet extends HttpServlet {
	AppContextListener app;
	ServiceImpl serviceImpl = ServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		app = new AppContextListener();
		app.contextInitialized(null);
		try {
            
			List<Image> allImage = serviceImpl.findAllImage();
			
			HttpSession session = req.getSession();
			
			List<Cloth> allCloth = serviceImpl.findAllCloth();
			
			DataManager.inputData("allCloth", allCloth);
			
			for (int i = 0; i < allImage.size(); i++) {
				if (allImage.get(i).getImg_num() == 2) {
					session.setAttribute("image1", allImage.get(i).getImg_64());
				} else if (allImage.get(i).getImg_num() == 3) {
					session.setAttribute("image2", allImage.get(i).getImg_64());
				} else if (allImage.get(i).getImg_num() == 4) {
					session.setAttribute("image3", allImage.get(i).getImg_64());
				} else if (allImage.get(i).getImg_num() == 35) {
					session.setAttribute("good", allImage.get(i).getImg_64());
				} else if (allImage.get(i).getImg_num() == 36) {
					session.setAttribute("bad", allImage.get(i).getImg_64());
				}
			}
    		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}