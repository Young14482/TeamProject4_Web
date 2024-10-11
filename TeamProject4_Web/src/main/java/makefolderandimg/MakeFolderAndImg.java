package makefolderandimg;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class MakeFolderAndImg {
    public static void main(String[] args) {
        String childDirectoryPath = "src/main/webapp/static/image/cloth/cloth?"; 

        createDirectoryIfNotExists(childDirectoryPath); 
    }

    public static void createDirectoryIfNotExists(String directoryPath) {
        Path path = Paths.get(directoryPath);

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("폴더 추가 완료");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("실패");
        }
    }
}
