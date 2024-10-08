package user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

	@Select("Select user_pw from user where user_ID = #{userId}")
	String Pw(@Param("userId") String userId);

	@Insert("insert into user (user_ID, user_pw, user_phone, user_name , user_birth, user_address,user_gender, user_grade, user_useMoney)"
			+ "values (#{user.id} , #{user.pw} ,#{user.phone} , #{user.name}, #{user.birth},"
			+ "#{user.address},#{user.gender}, #{user.grade}, #{user.useMoney} )")
	int insertUser(@Param("user") User user);
}
