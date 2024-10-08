package main;

import java.util.List;

import image.Image;
import material.Cloth;
import shoppingCart.ShoppingCartItem;

public interface Service {

	boolean insertImageToImg(String imageName, String base64Str);

	boolean insertImage(String imageName, String base64Str);
	
	List<Image> findAllImage();
	
	List<Cloth> findAllCloth();
	
	List<Cloth> findSearchCloth(String gender, int parsedSeason, int parsedColor, int parsedUsage, int parsedMinPrice, int parsedMaxPrice);
	
	List<ShoppingCartItem> selectShoppingCart(String userId);
}
