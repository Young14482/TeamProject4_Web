package user;

import java.util.List;

public interface UserService {
	
	String Pw(String userId);
	
	int InsertUser(User user);
	// 비밀번호 찾기
	String UserPw(String UserName, String UserId);
	// 아이디 찾기
	String UserId(String UserName, String UserBrith);
	// 비밀번호 변경
	int userChangePw(String userName, String UserId, String Pw);
}
