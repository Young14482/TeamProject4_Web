package main;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main")
public class MainHtmlServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
            // 절대 경로를 사용하여 파일 읽기
            String filePath = "C:\\Users\\GGG\\Desktop\\캡처2.PNG";
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            
            // Base64로 인코딩
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            
            HttpSession session = req.getSession();
    		session.setAttribute("base64Image", base64Encoded);
    		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		

	}

}
