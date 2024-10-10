package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import main.ServiceImpl;
import material.AppContextListener;

public class InsertImage {
	
	
	public static String encodeImageToBase64(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    	AppContextListener app = new AppContextListener();
    	app.contextInitialized(null);
    	// 해당 클래스 사용법
    	// imageName에 이미지 이름을 작성합니다.
    	// imagePath에 자신이 넣고싶은 이미지의 경로를 작성합니다.
    	// 해당 클래스를 따로 실행시키면 db에 변환된 데이터가 저장됩니다.

//    	int count = 1;
//    	
//    	while (count < 10) {
//    		String imageName = "옷" + count;
//            String imagePath = "C:\\Users\\GGG\\Desktop\\옷"+count+".PNG";
//            String base64Image = encodeImageToBase64(imagePath);
//            
//            boolean result = ServiceImpl.getInstance().insertImage(imageName, base64Image);
//            
//            if (result) {
//            	System.out.println(imageName + "insert 성공!");
//            } else {
//            	System.out.println("실패");
//            }
//            count++;
//    	}

		String imagePath = "C:\\Users\\GGG\\Desktop\\옷1내용\\설명5.PNG";
		String base64Image = encodeImageToBase64(imagePath);

		boolean result = ServiceImpl.getInstance().insertImageDetail(base64Image, 1);

		if (result) {
			System.out.println("insert 성공!");
		} else {
			System.out.println("실패");
		}

	}
}

