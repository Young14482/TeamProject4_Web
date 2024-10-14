package user;

import java.util.List;

public interface UserService {
	
	String Pw(String Id);
	
	int InsertUser(User user);
	// 비밀번호 찾기
	String UserPw(String UserName, String UserId);
	// 아이디 찾기
	String UserId(String UserName, String UserBrith);
	// 비밀번호 변경
	int userChangePw(String userName, String UserId, String Pw);
	// 아이디로 회원 정보 출력
	User userModify(String userId);
	// 유저 정보 변경
	int userChangeModify(String userName, String userPhone, String userAddress, String userId);
	// 아이디 중복 체크
	int userIdCheck(String userId);
	// 비밀번호 변경2
	int userChangePw2(String UserId, String Pw);
	// 회원탈퇴 구현
	int userLeave(String userId);
	// 전화번호 있는지 검증
	List<String> selectPhone();
}
