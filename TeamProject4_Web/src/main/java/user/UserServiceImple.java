package user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

public class UserServiceImple implements UserService {
	private static final UserServiceImple instance = new UserServiceImple();

	UserServiceImple() {
	}

	public static UserServiceImple getInstance() {
		return instance;
	}

	// 로그인시 아이디 입력
	@Override
	public String Pw(String Id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			String list = mapper.Pw(Id);
			if(list != null) {
				return list;
			}
		}
		return null;
	}
	
	// 회원가입
	@Override
	public int InsertUser(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			
			if(user != null) {
				int result = mapper.insertUser(user);
				if(result >= 0) {
					sqlSession.commit();
				}
				return result;
			}
		}
		return -1;
		
	}

	// 유저의 비밀번호 찾기
	@Override
	public String UserPw(String UserName, String UserId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			
			String UserPw = mapper.UserPw(UserId, UserName);
			
			if(UserPw != null) {
				return UserPw;
			}
			
		}
		return null;
	}
	// 유저의 아이디 찾기
	@Override
	public String UserId(String UserName, String UserBrith) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			
			String UserId = mapper.UserId(UserName, UserBrith);
			
			if(UserId != null) {
				
				return UserId;
			}
			
		}
		return null;
		
	}
	// 비밀번호 변경
	@Override
	public int userChangePw(String userName, String UserId, String Pw) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int userchanePw = mapper.userChangePw(userName, UserId, Pw);
			if(userchanePw != 0) {
				sqlSession.commit();
				return userchanePw;
			}
		}
		return 0;
	}
	
}
