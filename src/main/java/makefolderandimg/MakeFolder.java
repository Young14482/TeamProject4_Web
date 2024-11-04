package makefolderandimg;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class MakeFolder {
    public static void main(String[] args) {
        String childDirectoryPath = "src/main/webapp/static/image/cloth/cloth?"; 

        createDirectoryIfNotExists(childDirectoryPath); 
    }

    public static void createDirectoryIfNotExists(String directoryPath) {
        // 주어진 디렉토리 경로를 Path 객체로 변환합니다.
        Path path = Paths.get(directoryPath);

        // 해당 경로에 디렉토리가 존재하지 않는지 확인합니다.
        if (Files.notExists(path)) {
            try {
                // 디렉토리가 존재하지 않는다면, 디렉토리를 생성합니다.
                Files.createDirectories(path);
            } catch (IOException e) {
                // 예외가 발생할 경우, 예외 처리를 합니다.
            }
        }
    }

}
