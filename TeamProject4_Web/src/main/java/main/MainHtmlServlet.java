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
            
            for (Image image : allImage) {
				if (image.getImg_num() == 1) {
					session.setAttribute("image1", image.getImg_64());
				}
			}
    		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
