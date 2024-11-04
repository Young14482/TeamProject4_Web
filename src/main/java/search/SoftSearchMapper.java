package search;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import material.Cloth;

public interface SoftSearchMapper {

	@Select("SELECT * FROM lp.cloth as a \r\n"
			+ "join categorys as b on a.cloth_categorys = b.categorys_num\r\n"
			+ "join cloth_img as c on a.cloth_num = c.cloth_num;")
	List<Cloth> findAllCloth();
}
