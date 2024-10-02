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
		return 0;
		
	}
	
}
