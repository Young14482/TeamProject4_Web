package clothdetail;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClothReviewMapper {

	@Select("select * from review_collect\r\n"
			+ "where cloth_num = #{ cloth_num };")
	List<Review> findReview(@Param("cloth_num") int cloth_num);
}