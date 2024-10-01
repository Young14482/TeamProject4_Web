package user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

	@Select("Select user_pw from user where user_Id = #{Id}")
	String Pw(@Param("Id") String Id);
}
