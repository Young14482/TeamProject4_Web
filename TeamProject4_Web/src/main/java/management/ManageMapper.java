package management;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import lombok.experimental.PackagePrivate;

// 작성자 : 이나겸

public interface ManageMapper {
	
// <가입 회원 관리 페이지>------------------------------------------------------------------------------------------------
	
	// 관리자와 차단된 회원 제외한 가입 회원 목록 조회
	// 한 회원이 여러개의 배송지를 갖고있다면 가입회원관리페이지 표에는 배송지 하나만 나타날수있도록
	@Select("SELECT a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a "
			+ "WHERE a.user_block != 1 AND a.user_id != 'admin'")
	List<JoinUser> getJoinUser();
	
	// 여러개의 배송지를 갖고 있는 회원의 모든 배송지 조회
	@Select("SELECT deliveryaddress FROM deliveryaddress WHERE user_id = #{user_id}")
	List<String> getAllDeliveryAddress(@Param("user_id") String userId);
	
	// 관리자와 차단된 회원 제외한 가입 회원 수 조회
	@Select("select count(*) from lp.user where user_block != 1 and user_id != 'admin'")
	int getJoinUserCount();
	
	// 회원 아이디로 회원 조회
	@Select("SELECT a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a WHERE a.user_block != 1 AND a.user_id = #{user_id}")
	List<JoinUser> getSelectById(@Param("user_id") String userId);
	
	// 등급 별 회원 목록 조회
	@Select("SELECT a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
			+ "(SELECT b.deliveryaddress FROM deliveryaddress AS b WHERE b.user_id = a.user_id LIMIT 1) AS deliveryaddress \r\n"
			+ "FROM user AS a WHERE a.user_block != 1 AND a.user_grade = #{user_grade}")
	List<JoinUser> getSelectByGrade(@Param("user_grade") String user_grade);
	
	// 등급 별 회원 수 조회
	@Select("select count(*) from user where user_block != 1 and user_grade = #{user_grade}")
	int getCountByGrade(@Param("user_grade") String user_grade);
	
	// 성별 별 회원 목록 조회
	@Select("SELECT a.user_id, a.user_name, a.user_gender, a.user_birth, a.user_phone, a.user_address, a.user_grade, \r\n"
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
	@Select("select user_id, user_name, user_gender, user_birth, user_phone, user_address, user_grade "
			+ "from lp.user where user_block = 1")
	List<JoinUser> getBlockUser();
	
	// 차단된 회원 수 조회
	@Select("select count(*) from lp.user where user_block = 1")
	int getBlockUserCount();
	
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
			+ "join user as b on a.user_id = b.user_id\r\n"
			+ "join cloth as c on a.cloth_num = c.cloth_num")
	int getAllSales();
	
	// 회원 별 판매 내역 조회
	@Select("select b.user_id, b.user_name, b.user_phone, b.user_address, b.user_grade,\r\n"
			+ "a.payment_count, a.payment_date, c.cloth_name, c.cloth_brand, c.cloth_price,\r\n"
			+ "(c.cloth_price * a.payment_count)\r\n"
			+ "from payment as a\r\n"
			+ "join user as b on a.user_id = b.user_id\r\n"
			+ "join cloth as c on a.cloth_num = c.cloth_num")
	List<JoinUser> getSalesHistory();
	
// <소비자 구매 현황 페이지>------------------------------------------------------------------------------------------------
	
	// 소비자 별 구매 현황 목록 조회
	// 관리자 제외함, 차단 회원은 제외하지 않음
	// user_useMoney 업데이트 필요 (241006 user_useMoney 전부 0인 상태)
	@Select("select user_id, user_name, user_phone, user_address, user_grade, user_useMoney "
			+ "from lp.user where user_id != 'admin'")
	List<JoinUser> getPurchaseStatus();
	
	
// <상품 관리 페이지>-----------------------------------------------------------------------------------------------------
	
	// 상품 목록 조회
	@Select("select a.cloth_name, a.cloth_brand, a.cloth_price, a.cloth_gender, \r\n"
			+ "b.cloth_size_s, b.cloth_size_m, b.cloth_size_l, b.cloth_size_xl, b.cloth_size_xxl \r\n"
			+ "from cloth as a\r\n"
			+ "join inventory as b on a.cloth_num = b.cloth_num")
	List<Cloth> getAllCloth();
	
	// 전체 품목 수 조회
	@Select("select count(*) from cloth")
	int getAllClothCount();
	
	// 상품 추가
//	@Insert()
	
	// 상품 정보 수정
//	@Update()
	
	// 상품 삭제
	@Delete("delete from cloth where cloth_num = #{cloth_num}")
	int deleteCloth(@Param("cloth_num") int cloth_num);
}