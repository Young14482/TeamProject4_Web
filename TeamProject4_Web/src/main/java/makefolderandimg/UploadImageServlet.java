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

@WebServlet("/uploadImage")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String param = (String) session.getAttribute("plusClothNumber");

		if (param != null) {
			int plusClothNumber = Integer.parseInt(param);

			String uploadPath = "D:/moohyun/workspace EE/TeamProject4_Web/TeamProject4_Web/src/main/webapp/static/image/cloth/cloth"
					+ plusClothNumber; 
			MakeFolderAndImg.createDirectoryIfNotExists(uploadPath);
			String divide = req.getParameter("divide");
			if (divide != null) {
				
				int divideParsedInt = Integer.parseInt(divide);
				
				if (divideParsedInt == 1) {
					insertImage(req, "옷" + plusClothNumber + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload1.jsp").forward(req, resp);
				} else if (divideParsedInt == 2) {
					insertImage(req, "1" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload2.jsp").forward(req, resp);
				} else if (divideParsedInt == 3) {
					insertImage(req, "2" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload3.jsp").forward(req, resp);
				} else if (divideParsedInt == 4) {
					insertImage(req, "3" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload4.jsp").forward(req, resp);
				} else if (divideParsedInt == 5) {
					insertImage(req, "1-1" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload5.jsp").forward(req, resp);
				} else if (divideParsedInt == 6) {
					insertImage(req, "1-2" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload6.jsp").forward(req, resp);
				} else if (divideParsedInt == 7) {
					insertImage(req, "1-3" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload7.jsp").forward(req, resp);
				} else if (divideParsedInt == 8) {
					insertImage(req, "1-4" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload8.jsp").forward(req, resp);
				} else if (divideParsedInt == 9) {
					insertImage(req, "1-5" + ".png", uploadPath);
					req.getRequestDispatcher("/WEB-INF/views/imageUpload9.jsp").forward(req, resp);
				}
				
			}

		} else {
			System.out.println("plusClothNumber를 세션에 넣으세요");
			req.getRequestDispatcher("/WEB-INF/views/imageUpload.jsp").forward(req, resp);
		}

	}

	private void insertImage(HttpServletRequest req, String newFileName, String uploadPath)
			throws IOException, ServletException {
		Part filePart = req.getPart("file");
		String originalFileName = filePart.getSubmittedFileName();

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		File file = new File(uploadPath + File.separator + newFileName);
		try (InputStream input = filePart.getInputStream()) {
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
