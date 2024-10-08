package main;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import image.ClothImage;
import image.Image;
import material.Cloth;

public interface Mapper {

	
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
	
}
