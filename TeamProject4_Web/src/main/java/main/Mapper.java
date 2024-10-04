package main;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import image.Image;
import material.Cloth;

public interface Mapper {

	
	@Insert("insert into img (img_name, img_64) values (#{ imageName }, #{ base64Str });")
	int insertImage(@Param("imageName") String imageName, @Param("base64Str") String base64Str);
	
	@Select("select * from img")
	List<Image> findAllImage();

	@Select("SELECT * FROM lp.cloth as a \r\n"
			+ "join categorys as b on a.cloth_categorys = b.categorys_num;")
	List<Cloth> findAllCloth();
}
