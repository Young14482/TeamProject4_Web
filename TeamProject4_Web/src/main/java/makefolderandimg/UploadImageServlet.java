package makefolderandimg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import main.ServiceImpl;

@WebServlet("/uploadImage")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int lastInsertNumber = ServiceImpl.getInstance().lastInsertClothNum();
		HttpSession session = req.getSession();
		session.setAttribute("plusClothNumber", lastInsertNumber);
		
		req.getRequestDispatcher("/WEB-INF/views/imageUpload.jsp").forward(req, resp);
	}

	private void insertImage(HttpServletRequest req, String newFileName, String uploadPath)
	        throws IOException, ServletException {
	    // "file"이라는 이름으로 업로드된 파일을 가져옵니다.
	    Part filePart = req.getPart("file");
	    // 원래의 파일 이름을 가져옵니다.
	    // String originalFileName = filePart.getSubmittedFileName();

	    // 업로드할 디렉토리를 만듭니다. 디렉토리가 존재하지 않으면 생성합니다.
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }

	    // 새 파일 경로를 설정하고, 입력 스트림을 사용해 파일을 저장합니다.
	    File file = new File(uploadPath + File.separator + newFileName);
	    try (InputStream input = filePart.getInputStream()) {
	        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    Integer param = (Integer) session.getAttribute("plusClothNumber");
	    if (param != null) {
	        int plusClothNumber = param;
	        String uploadPath = "D:/moohyun/workspace EE/TeamProject4_Web/TeamProject4_Web/src/main/webapp/static/image/cloth/cloth"
	                + plusClothNumber;
	        String divide = req.getParameter("divide");

	        int divideParsedInt = Integer.parseInt(divide);
	        if (divideParsedInt == 1) {
	            insertImage(req, "옷" + plusClothNumber + ".png", uploadPath);
	        } else if (divideParsedInt == 2) {
	            insertImage(req, "1.png", uploadPath);
	        } else if (divideParsedInt == 3) {
	            insertImage(req, "2.png", uploadPath);
	        } else if (divideParsedInt == 4) {
	            insertImage(req, "3.png", uploadPath);
	        } else if (divideParsedInt == 5) {
	            insertImage(req, "1-1.png", uploadPath);
	        } else if (divideParsedInt == 6) {
	            insertImage(req, "1-2.png", uploadPath);
	        } else if (divideParsedInt == 7) {
	            insertImage(req, "1-3.png", uploadPath);
	        } else if (divideParsedInt == 8) {
	            insertImage(req, "1-4.png", uploadPath);
	        } else if (divideParsedInt == 9) {
	            insertImage(req, "1-5.png", uploadPath);
	        }

	        resp.setContentType("text/plain");
	        resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write("파일 업로드 성공");
	    } else {
	        resp.setContentType("text/plain");
	        resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write("파일 업로드 실패");
	    }
	}


}
