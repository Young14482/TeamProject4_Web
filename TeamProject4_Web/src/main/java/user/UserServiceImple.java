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
			String Pw = mapper.Pw(Id);
			return Pw;
		}
	}
}
