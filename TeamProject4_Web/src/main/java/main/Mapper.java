package main;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import image.ClothImage;
import image.Image;
import material.Cloth;
import shoppingCart.ShoppingCartItem;

public interface Mapper {

	@Insert("insert into img (img_name, img_64) values (#{ imageName }, #{ base64Str });")
	int insertImageToImg(@Param("imageName") String imageName, @Param("base64Str") String base64Str);
	
	@Insert("insert into cloth_img (image_name, list_image) values (#{ imageName }, #{ base64Str });")
	int insertImage(@Param("imageName") String imageName, @Param("base64Str") String base64Str);

	@Select("select * from img")
	List<Image> findAllImage();

	@Select("SELECT * FROM lp.cloth as a \r\n"
			+ "join categorys as b on a.cloth_categorys = b.categorys_num\r\n"
			+ "join cloth_img as c on a.cloth_num = c.cloth_num;")
	List<Cloth> findAllCloth();

	@Select("SELECT cloth_num, image_name, list_image, main_image1, main_image2, main_image3 FROM lp.cloth_img;")
	List<ClothImage> findAllClothImage();

	//main_image1
	//explanation_image1
	@Update("UPDATE cloth_img\r\n"
			+ "SET explanation_image5 = #{ base64Str }\r\n"
			+ "WHERE cloth_num = #{ cloth_num };")
	int insertClothDetailImg(@Param("base64Str") String base64Str, @Param("cloth_num") int cloth_num);
	
	@Select("SELECT * FROM lp.cloth as a \r\n"
	        + "join categorys as b on a.cloth_categorys = b.categorys_num \r\n"
	        + "join cloth_img as c on a.cloth_num = c.cloth_num \r\n"
	        + "where (b.season_category = #{parsedSeason} OR #{parsedSeason} = 6) \r\n"
	        + "and (a.cloth_gender = #{parsedGender} OR #{parsedGender} = '다줘') \r\n"
	        + "and (b.color_category = #{parsedColor} or #{parsedColor} = 7) \r\n"
	        + "and (b.usage_category1 = #{parsedUsage} or b.usage_category2 = #{parsedUsage} or b.usage_category3 = #{parsedUsage} or #{parsedUsage} = 8) \r\n"
	        + "and a.cloth_price between #{parsedMinPrice} and #{parsedMaxPrice};")
	List<Cloth> findSearchCloth(@Param("parsedGender") String gender
						, @Param("parsedSeason") int parsedSeason
						, @Param("parsedColor") int parsedColor
						, @Param("parsedUsage") int parsedUsage
						, @Param("parsedMinPrice") int parsedMinPrice
						, @Param("parsedMaxPrice") int parsedMaxPrice);
	
	@Select("SELECT a.user_ID, b.cloth_num, b.cloth_price, a.shoppingcart_count, b.cloth_name, c.list_image FROM lp.shoppingcart AS a \r\n"
			+ "JOIN cloth AS b ON a.cloth_num = b.cloth_num\r\n"
			+ "JOIN cloth_img AS c ON c.cloth_num = b.cloth_num WHERE a.user_ID = #{userId};")
    List<ShoppingCartItem> selectShoppingCart(@Param("userId") String userId);

	@Delete("DELETE FROM lp.shoppingcart WHERE cloth_num = #{clothNum}")
    int deleteFromShoppingCart(@Param("clothNum") int clothNum);
	
	@Insert("INSERT INTO lp.payment (user_id, cloth_num, payment_count) VALUES (#{user_Id}, #{cloth_num}, #{shoppingcart_count});")
	int insertPayment(ShoppingCartItem order);
}
