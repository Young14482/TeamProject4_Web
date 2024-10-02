package main;

import java.util.List;

import image.Image;
import material.Cloth;

public interface Service {


	boolean insertImage(String imageName, String base64Str);
	
	List<Image> findAllImage();
	
	List<Cloth> findAllCloth();
}
