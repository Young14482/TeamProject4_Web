package management;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

public class ManageServiceImpl implements ManageService {
	
	private static final ManageServiceImpl instance = new ManageServiceImpl();
	
	private ManageServiceImpl() {
	}
	
	public static ManageServiceImpl getInstance() {
		
		return instance;
	}

	// 회원 등급 수정 메소드 (user_grade update)
	@Override
	public JoinUser updateUserGrade(JoinUser joinUser) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			// 회원 등급 수정하고 수정된 행 수를 반환받음
			int rows = manageMapper.changeUserGrade(joinUser);
			
			// 수정된 행이 1개인 경우
			if (rows == 1) {
				sqlSession.commit(); // 트랜잭션 커밋
				return joinUser; // 수정된 회원정보 반환
			}
		}
		return null; // 수정 실패하면 null 반환
	}

	// 회원 차단 여부 수정 메소드 (user_block update)
	@Override
	public JoinUser updateUserBlock(JoinUser joinUser) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			
			// 회원 차단 여부 수정하고 수정된 행 수 반환받음
			int rows = manageMapper.makeBlockUser(joinUser);
			
			// 수정된 행이 1개인 경우
			if (rows == 1) {
				sqlSession.commit(); // 트랜잭션 커밋
				return joinUser; // 수정된 회원정보 반환
			}
		}
		return null; // 수정 실패하면 null 반환
	}
}