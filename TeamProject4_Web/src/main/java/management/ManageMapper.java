package management;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import lombok.experimental.PackagePrivate;

// 작성자 : 이나겸

public interface ManageMapper {

// <가입 회원 관리 페이지>------------------------------------------------------------------------------------------------

	// 관리자와 차단된 회원 제외한 가입 회원 목록 조회
	// 한 회원이 여러개의 배송지를 갖고있다면 가입회원관리페이지 표에는 배송지 하나만 나타날수있도록
	@Select("SELECT row_number() over() as no, a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a " + "WHERE a.user_block != 1 AND a.user_id != 'admin'")
	List<JoinUser> getJoinUser();

	// 여러개의 배송지를 갖고 있는 회원의 모든 배송지 조회
	@Select("SELECT deliveryaddress FROM deliveryaddress WHERE user_id = #{user_id}")
	List<String> getAllDeliveryAddress(@Param("user_id") String userId);

	// 관리자와 차단된 회원 제외한 가입 회원 수 조회
	@Select("select count(*) from lp.user where user_block != 1 and user_id != 'admin'")
	int getJoinUserCount();

	// 회원 아이디로 회원 조회 (무조건 한 사람만 나와야함)
	@Select("SELECT row_number() over() as no, a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a WHERE a.user_block != 1 AND a.user_id = #{user_id}")
	List<JoinUser> getSelectById(@Param("user_id") String userId);

	// 회원 아이디 별 회원 수 (무조건 1이 나와야함)
	@Select("select count(*) from user where user_block != 1 and user_id = #{user_id}")
	int getCountById(@Param("user_id") String userId);

	// 등급 별 회원 목록 조회
	@Select("SELECT row_number() over() as no, a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a WHERE a.user_block != 1 AND a.user_grade = #{user_grade}")
	List<JoinUser> getSelectByGrade(@Param("user_grade") String user_grade);

	// 등급 별 회원 수 조회
	@Select("select count(*) from user where user_block != 1 and user_grade = #{user_grade}")
	int getCountByGrade(@Param("user_grade") String user_grade);

	// 성별 별 회원 목록 조회
	@Select("SELECT row_number() over() as no, a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a WHERE a.user_block != 1 AND a.user_id != 'admin' AND a.user_gender = #{user_gender}")
	List<JoinUser> getSelectByGender(@Param("user_gender") String user_gender);

	// 성별 별 회원 수 조회
	@Select("select count(*) from user where user_block != 1 and user_id != 'admin' and user_gender = #{user_gender}")
	int getCountByGender(@Param("user_gender") String user_gender);

	// 회원 등급 변경
	@Update("update user set user_grade = #{user_grade} where user_id = #{user_id}")
	int changeUserGrade(JoinUser joinUser);

	// 회원 차단
	@Update("update user set user_block = 1 where user_id = #{user_id}")
	int makeBlockUser(JoinUser joinUser);

	// 탈퇴 회원 복원 => deleteuser 테이블에서 delete하고 user 테이블에 다시 insert

// <차단 회원 관리 페이지>------------------------------------------------------------------------------------------------

	// 차단된 회원 목록 조회
	@Select("select row_number() over() as no, user_id, user_name, user_gender, user_birth, user_phone, user_address, user_grade "
			+ "from lp.user where user_block = 1")
	List<JoinUser> getBlockUser();

	// 차단된 회원 수 조회
	@Select("select count(*) from lp.user where user_block = 1")
	int getBlockUserCount();

	// 회원 아이디로 차단 회원 조회 (무조건 한 사람만 나와야함)
	@Select("SELECT row_number() over() as no, user_id, user_name, user_gender, user_birth, user_phone, user_address, user_grade\r\n"
			+ "FROM user WHERE user_block = 1 AND user_id = #{user_id}")
	List<JoinUser> getSelectBlockById(@Param("user_id") String userId);

	// 회원 아이디 별 차단 회원 수 (무조건 1이 나와야함)
	@Select("select count(*) from user where user_block = 1 and user_id = #{user_id}")
	int getCountBlockById(@Param("user_id") String userId);

	// 등급 별 차단 회원 목록 조회
	@Select("SELECT row_number() over() as no, user_id, user_name, user_gender, user_birth, user_phone, user_address, user_grade\r\n"
			+ "FROM user WHERE user_block = 1 AND user_grade = #{user_grade}")
	List<JoinUser> getSelectBlockByGrade(@Param("user_grade") String user_grade);

	// 등급 별 차단 회원 수 조회
	@Select("select count(*) from user where user_block = 1 and user_grade = #{user_grade}")
	int getCountBlockByGrade(@Param("user_grade") String user_grade);

	// 성별 별 차단 회원 목록 조회
	@Select("SELECT row_number() over() as no, user_id, user_name, user_gender, user_birth, user_phone, user_address, user_grade\r\n"
			+ "FROM user WHERE user_block = 1 AND user_gender = #{user_gender}")
	List<JoinUser> getSelectBlockByGender(@Param("user_gender") String user_gender);

	// 성별 별 차단 회원 수 조회
	@Select("select count(*) from user where user_block = 1 and user_gender = #{user_gender}")
	int getCountBlockByGender(@Param("user_gender") String user_gender);

// <탈퇴 회원 관리 페이지>------------------------------------------------------------------------------------------------

	// 탈퇴 회원 목록 조회
	@Select("select user_id, user_pw, user_phone from lp.deleteuser")
	List<JoinUser> getLeaveUser();

	// 탈퇴 회원 수 조회
	@Select("select count(*) from lp.deleteuser")
	int getLeaveUserCount();

	// 탈퇴 회원 복원 => deleteuser 테이블에서 delete하고 user 테이블에 다시 insert

// <총 매출 페이지>------------------------------------------------------------------------------------------------	

	// 총 매출액 조회
	@Select("select sum(a.payment_count * c.cloth_price) from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num")
	int getAllSales();

	// 회원 구매 내역 조회
	@Select("select row_number() over() as no, b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n" + "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num")
	List<JoinUser> getSalesHistory();

	// 회원 아이디 별 구매한 상품 조회
	@Select("select row_number() over() as no, b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n" + "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where b.user_id = #{user_id}")
	List<JoinUser> getSelectSalesById(@Param("user_id") String userId);

	// 회원 아이디 별 매출액 조회
	@Select("select sum(a.payment_count * c.cloth_price) from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where b.user_id = #{user_id}")
	Integer getTotalSalesById(@Param("user_id") String userId);

	// 상품명 별 판매한 상품 조회
	@Select("select row_number() over() as no, b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n" + "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where c.cloth_name = #{cloth_name}")
	List<JoinUser> getSelectSalesByClothName(@Param("cloth_name") String cloth_name);

	// 상품명 별 매출액 조회
	@Select("select sum(a.payment_count * c.cloth_price) from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where c.cloth_name = #{cloth_name}")
	Integer getTotalSalesByClothName(@Param("cloth_name") String cloth_name);

	// 상품 브랜드 별 판매한 상품 조회
	@Select("select row_number() over() as no, b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n" + "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where c.cloth_brand = #{cloth_brand}")
	List<JoinUser> getSelectSalesByBrand(@Param("cloth_brand") String cloth_brand);

	// 상품 브랜드 별 매출액 조회
	@Select("select sum(a.payment_count * c.cloth_price) from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where c.cloth_brand = #{cloth_brand}")
	Integer getTotalSalesByBrand(@Param("cloth_brand") String cloth_brand);

	// 결제 일시 별 판매한 상품 조회 (시간 제외하고 날짜로만 검색하는데 숫자만 입력해도 검색할 수 있도록)
	@Select("select row_number() over() as no, b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n" + "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where DATE_FORMAT(a.payment_date, '%Y%m%d') LIKE CONCAT('%', #{payment_date}, '%')")
	List<JoinUser> getSelectSalesByDate(@Param("payment_date") String payment_date);

	// 결제 일시 별 매출액 조회
	@Select("select sum(a.payment_count * c.cloth_price) from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n" + "join cloth as c on a.cloth_num = c.cloth_num\r\n"
			+ "where DATE_FORMAT(a.payment_date, '%Y%m%d') LIKE CONCAT('%', #{payment_date}, '%')")
	Integer getTotalSalesByDate(@Param("payment_date") String payment_date);

// <소비자 구매 현황 페이지>------------------------------------------------------------------------------------------------

	// 전체 고객 별 구매 현황 목록 조회
	// 관리자 제외함, 차단 회원은 제외하지 않음
	// user_useMoney 업데이트 필요 (user_useMoney 전부 0인 상태)
	@Select("select row_number() over() as no, user_id, user_name, user_phone, user_address, user_grade, user_useMoney "
			+ "from lp.user where user_id != 'admin'")
	List<JoinUser> getPurchaseStatus();

	// 회원 아이디 별 구매 현황 목록 조회
	@Select("select row_number() over() as no, user_id, user_name, user_phone, user_address, user_grade, user_useMoney \r\n"
			+ "from user where user_id != 'admin' and user_id = #{user_id}")
	List<JoinUser> getSelectPurchaseById(@Param("user_id") String userId);

	// 회원 이름 별 구매 현황 목록 조회
	@Select("select row_number() over() as no, user_id, user_name, user_phone, user_address, user_grade, user_useMoney \r\n"
			+ "from user where user_id != 'admin' and user_name = #{user_name}")
	List<JoinUser> getSelectPurchaseByName(@Param("user_name") String user_name);

// <상품 관리 페이지>-----------------------------------------------------------------------------------------------------

	// 상품 목록 조회
	@Select("select row_number() over() as no, a.cloth_name, a.cloth_brand, a.cloth_price, a.cloth_gender, \r\n"
			+ "b.cloth_size_s, b.cloth_size_m, b.cloth_size_l, b.cloth_size_xl, b.cloth_size_xxl \r\n"
			+ "from cloth as a\r\n" + "join inventory as b on a.cloth_num = b.cloth_num")
	List<Cloth> getAllCloth();

	// 전체 품목 수 조회
	@Select("select count(*) from cloth")
	int getAllClothCount();

	// 작성자 : 이진석 라인
	// s
	@Select("Select category_num, category_name from s_category")
	@Results(value = { @Result(column = "category_num", property = "category_num"),
			@Result(column = "category_name", property = "category_name") })
	List<Category_s> selectS();

	// usage
	@Select("Select usage_num, usage_name from usage_category")
	@Results(value = { @Result(column = "usage_num", property = "usage_num"),
			@Result(column = "usage_name", property = "usage_name") })
	List<Category_usage> selectUsage();

	// color
	@Select("Select color_num, color_name from color_category")
	@Results(value = { @Result(column = "color_num", property = "color_num"),
			@Result(column = "color_name", property = "color_name") })
	List<Category_color> selectColor();

	// season
	@Select("Select season_num, season_name from season_category")
	@Results(value = { @Result(column = "season_num", property = "season_num"),
			@Result(column = "season_name", property = "season_name") })
	List<Category_season> selectSeason();

	// Cloth Insert
	@Insert("INSERT INTO Cloth (cloth_name, cloth_brand, cloth_price, cloth_min_size, cloth_max_size, cloth_explanation, cloth_gender) "
			+ "VALUES (#{cloth_name}, #{cloth_brand}, #{cloth_price}, #{cloth_min_size}, #{cloth_max_size}, #{cloth_explanation}, #{cloth_gender})")
	int insertCloth(Cloth cloth);

	// Inventory Insert
	@Insert("Insert into inventory (cloth_num, cloth_size_s, cloth_size_m, cloth_size_l,cloth_size_xl, cloth_size_xxl) "
			+ "values (#{cloth_num}, #{cloth_size_s},#{cloth_size_m},#{cloth_size_l},#{cloth_size_xl},#{cloth_size_xxl})")
	int insertInventory(Cloth cloth);

	// 2,3번 입력
	@Insert("Insert into categorys (cloth_num, season_category, color_category, s_category, usage_category1, usage_category2, usage_category3)"
			+ "values (#{clothNum}, #{categorys.season_category},#{categorys.color_category},#{categorys.s_category},#{categorys.usage_category1},#{categorys.usage_category2},#{categorys.usage_category3} )")
	int insertCategorys1(@Param("clothNum") int clothNum, @Param("categorys") Categorys categorys);

	// 3번 입력
	@Insert("Insert into categorys (cloth_num, season_category, color_category, s_category, usage_category1, usage_category3)"
			+ "values (#{clothNum}, #{categorys.season_category},#{categorys.color_category},#{categorys.s_category},#{categorys.usage_category1}, #{categorys.usage_category3} )")
	int insertCategorys2(@Param("clothNum") int clothNum, @Param("categorys") Categorys categorys);

	// 2번 입력
	@Insert("Insert into categorys (cloth_num, season_category, color_category, s_category, usage_category1, usage_category2)"
			+ "values (#{clothNum}, #{categorys.season_category},#{categorys.color_category},#{categorys.s_category},#{categorys.usage_category1},#{categorys.usage_category2} )")
	int insertCategorys3(@Param("clothNum") int clothNum, @Param("categorys") Categorys categorys);

	// 둘다 입력 안함
	@Insert("Insert into categorys (cloth_num, season_category, color_category, s_category, usage_category1)"
			+ "values (#{clothNum}, #{categorys.season_category},#{categorys.color_category},#{categorys.s_category},#{categorys.usage_category1} )")
	int insertCategorys4(@Param("clothNum") int clothNum, @Param("categorys") Categorys categorys);

	// 마지막 pk값
	@Select("SELECT LAST_INSERT_ID()")
	int SelectLastPk();

	// 카테고리 완성될때 카테고리 값 cloth에 삽입
	@Update("Update Cloth Set cloth_categorys = #{categoryPk} where cloth_num = #{clothPk}")
	int updateCloth(@Param("clothPk") int clothPk, @Param("categoryPk") int categoryPk);

	@Update("Update Cloth Set cloth_name = #{cloth.cloth_name}, cloth_price = #{cloth.cloth_price}, cloth_brand = #{cloth.cloth_brand},"
			+ "cloth_gender= #{cloth.cloth_gender} where cloth_num = #{cloth.cloth_num}")
	int updateInfoCloth(@Param("cloth") Cloth cloth);

	@Update("Update inventory Set cloth_size_s = #{cloth.cloth_size_s} , cloth_size_m = #{cloth.cloth_size_m},  cloth_size_l= #{cloth.cloth_size_l},"
			+ "cloth_size_xl= #{cloth.cloth_size_xl}, cloth_size_xxl= #{cloth.cloth_size_xxl} where cloth_num = #{cloth.cloth_num}")
	int updateInfoInventory(@Param("cloth") Cloth cloth);

	// 옷의 논리적 삭제
	@Update("Update Cloth set cloth_delete = 1 where cloth_num = #{cloth_num}")
	int logicalClothDelete(@Param("cloth_num") int cloth_num);
}