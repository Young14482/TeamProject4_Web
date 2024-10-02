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

	@Select("select * from cloth")
	List<Cloth> findAllCloth();
}
