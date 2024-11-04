package main;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import image.ClothImage;
import image.Image;
import material.Cloth;
import payment.ClothSize;
import payment.PayCloth;
import payment.PaymentInfo;
import shoppingCart.ShoppingCartItem;
import user.User;

public interface Mapper {

	@Insert("insert into img (img_name, img_64) values (#{ imageName }, #{ base64Str });")
	int insertImageToImg(@Param("imageName") String imageName, @Param("base64Str") String base64Str);
	
	@Insert("insert into cloth_img (image_name, list_image) values (#{ imageName }, #{ base64Str });")
	int insertImage(@Param("imageName") String imageName, @Param("base64Str") String base64Str);

	@Select("select * from img")
	List<Image> findAllImage();

	@Select("SELECT * FROM lp.cloth as a \r\n"
			+ "join categorys as b on a.cloth_categorys = b.categorys_num;")
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
	
	@Select("SELECT a.user_ID, b.cloth_num, b.cloth_price, a.shoppingcart_count, b.cloth_name FROM lp.shoppingcart AS a \r\n"
			+ "JOIN cloth AS b ON a.cloth_num = b.cloth_num\r\n"
			+ "WHERE a.user_ID = #{userId};")
    List<ShoppingCartItem> selectShoppingCart(@Param("userId") String userId);

	@Delete("DELETE FROM lp.shoppingcart WHERE cloth_num = #{clothNum}")
    int deleteFromShoppingCart(@Param("clothNum") int clothNum);
	
	
	// 장바구니 관련
	
	@Select("select shoppingcart_count from shoppingcart where user_ID = #{userId} and cloth_num = #{clothNum};")
	Integer findUserShoppingCartDetail(@Param("userId") String userId, @Param("clothNum") int clothNum);
	
	@Update("update shoppingcart set shoppingcart_count = #{count} where user_ID = #{userId} and cloth_num = #{clothNum};")
	int updateUserShoppingCartDetail(@Param("count") int count, @Param("userId") String userId, @Param("clothNum") int clothNum);
	
	@Insert("insert into shoppingcart(user_ID, cloth_num, shoppingcart_count) values (#{userId}, #{clothNum}, 1);")
	Integer insertUserShoppingCartDetail(@Param("userId") String userId, @Param("clothNum") int clothNum);

	
	// 유저가 결제완료한 옷들 관련
	
	@Select("select * from payment AS a\r\n"
			+ "join cloth AS b ON a.cloth_num = b.cloth_num\r\n"
			+ "where user_ID = #{userId} ;")
	List<PayCloth> findUserClothsToPay(@Param("userId") String userId);

	
	// 리뷰 관련
	
	@Insert("insert into review_collect(user_ID, cloth_num, reviewDetail, good_or_bad) values (#{userId}, #{cloth_num}, #{reviewDetail}, #{good_or_bad});")
	Integer insertReview(@Param("userId") String user_ID, @Param("cloth_num") int cloth_num, @Param("reviewDetail") String reviewDetail, @Param("good_or_bad") String good_or_bad);

	@Update("update payment set write_review = 1 where user_ID = #{userId} and cloth_num = #{cloth_num};")
	Integer updateUserStatus(@Param("userId") String user_ID, @Param("cloth_num") int cloth_num);
	
	@Update("update cloth set cloth_good = cloth_good + 1 where cloth_num = #{cloth_num};")
	Integer updateClothGood(@Param("cloth_num") int cloth_num);
	
	@Update("update cloth set cloth_bad = cloth_bad + 1 where cloth_num = #{cloth_num};")
	Integer updateClothBad(@Param("cloth_num") int cloth_num);

	
	// 주문을 하면 payment에 값을 삽입
	@Insert("INSERT INTO lp.payment (user_id, cloth_num, payment_count, cloth_size) VALUES (#{userId}, #{cloth_num}, #{payment_count}, #{cloth_size})")
	int insertPayment(@Param("userId") String userId, @Param("cloth_num") int clothNum, @Param("payment_count") int paymentCount, @Param("cloth_size") int clothSize);


	
	// 제일 마지막에 추가된 옷의 num을 가져옴
	@Select("SELECT cloth_num \r\n"
			+ "FROM cloth \r\n"
			+ "ORDER BY cloth_num DESC \r\n"
			+ "LIMIT 1;")
	int ClothLastNum();

	
	
	
	// 결제 취소
	// 취소할 옷의 결제금액 조회
	@Select("SELECT payment_count * cloth_price AS totalPayPrice, payment_count \r\n"
	        + "FROM payment AS a\r\n"
	        + "JOIN cloth AS b ON a.cloth_num = b.cloth_num\r\n"
	        + "WHERE a.user_ID = #{userId} AND a.cloth_num = #{cloth_num} AND a.delivery_status = 0 \r\n"
	        + "LIMIT 1")
	@Results({
	    @Result(property = "totalPayPrice", column = "totalPayPrice"),
	    @Result(property = "paymentCount", column = "payment_count")
	})
	PaymentInfo findUserPayPrice(@Param("userId") String user_Id, @Param("cloth_num") int cloth_num);


	
	// payment 테이블에서 해당 행을 삭제
	@Delete("DELETE FROM payment\r\n"
	        + "WHERE cloth_num = #{cloth_num}\r\n"
	        + "AND user_ID = #{userId}\r\n"
	        + "AND delivery_status = 0 \r\n"
	        + "LIMIT 1")
	int cancelOrder(@Param("userId") String user_Id, @Param("cloth_num") int cloth_num);

	
	// 유저 사용금액을 취소한 만큼 줄임
	@Update("update user set user_useMoney = user_useMoney - #{downMoney} where user_ID = #{userId};")
	int changeUserUseMoneyToDown(@Param("userId") String user_Id, @Param("downMoney") int downMoney);
	
	// 취소한 개수만큼 옷의 sold 값을 내림
	@Update("update cloth set cloth_sold = cloth_sold - #{downSold} where cloth_num = #{cloth_num};")
	int changeClothSoldToDown(@Param("downSold") int downSold, @Param("cloth_num") int cloth_num);
	

	@Select("SELECT user_id, user_phone, user_address FROM lp.user where user_Id = #{userId};")
	@Results({
		 @Result(property = "id", column = "user_id"),
		 @Result(property = "phone", column = "user_phone"),
		 @Result(property = "address", column = "user_address"),
	})
	User getUser(@Param("userId") String userId);

	@Select("SELECT user_useMoney FROM lp.user where user_Id = #{userId};")
	int getUseMoney(@Param("userId") String userId);

	@Update("UPDATE user SET user_useMoney = #{usedMoney} WHERE user_Id = #{userId};")
	int updateUseMoney(@Param("userId") String userId, @Param("usedMoney") int usedMoney);
	
	@Select("SELECT cloth_sold FROM lp.cloth where cloth_num = #{clothNum};")
	int getClothSold(@Param("clothNum") int clothNum);

	@Update("UPDATE cloth SET cloth_sold = #{count} WHERE cloth_num = #{clothNum};")
	void updateClothSold(@Param("clothNum") int clothNum, @Param("count") int count);
	
	
	
	// 옷 사이즈 
	@Select("SELECT cloth_min_size, cloth_max_size FROM lp.cloth where cloth_num = #{clothNum};")
	ClothSize findClothSize(@Param("clothNum") int clothNum);
	
	
	
	
	
	
	
	
	
	
}
