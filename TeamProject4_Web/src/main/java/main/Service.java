package main;

import java.util.List;

import image.Image;

public interface Service {


	boolean insertImage(String imageName, String base64Str);
	
	List<Image> findAllImage();
}
