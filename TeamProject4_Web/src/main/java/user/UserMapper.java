package user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

	@Select("Select user_pw from user where user_ID = #{userId}")
	String Pw(@Param("userId") String userId);

	@Insert("insert into user (user_ID, user_pw, user_phone, user_name , user_birth, user_address,user_gender, user_grade, user_useMoney)"
			+ "values (#{user.id} , #{user.pw} ,#{user.phone} , #{user.name}, #{user.birth},"
			+ "#{user.address},#{user.gender}, #{user.grade}, #{user.useMoney} )")
	int insertUser(@Param("user") User user);

	@Select("Select count(user_pw) from user where user_ID = #{userId} AND user_name = #{userName}")
	String UserPw(@Param("userId") String userId, @Param("userName") String userName);

	@Select("Select user_ID from user where user_name = #{userName} AND user_birth = #{userBrith}")
	String UserId(@Param("userName") String userName, @Param("userBrith") String userBrith);

	@Update("update user Set user_pw = #{userPw} where user_id = #{userId} And user_name = #{userName}")
	int userChangePw(@Param("userName") String userName, @Param("userId") String userId,
			@Param("userPw") String userPw);

	@Select("Select user_name, user_address, user_phone ,user_grade from user where user_ID = #{userId}")
	@Results(value = { @Result(column = "user_name", property = "name"),
			@Result(column = "user_address", property = "address"), @Result(column = "user_phone", property = "phone"),
			@Result(column = "user_grade", property = "grade") })
	User userModify(@Param("userId") String userId);

	@Update("update user Set user_name = #{userName}, user_phone = #{userPhone}, user_address = #{userAddress} where user_Id = #{userId}")
	int userChangeModify(@Param("userName") String userName, @Param("userPhone") String userPhone,
			@Param("userAddress") String userAddress, @Param("userId") String userId);

	@Select("Select count(user_Id) from user where user_Id = #{userId}")
	int userIdCheck(@Param("userId") String userId);

	@Update("update user Set user_pw = #{userPw} where user_id = #{userId}")
	int userChangePw2(@Param("userId") String userId, @Param("userPw") String userPw);

	@Update("update user Set user_leave = 1 where user_id = #{userId}")
	int userLeave(@Param("userId") String userId);
	
	@Select("Select user_phone from user")
	List<String>selectPhone();
}
