package management;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

public class ManageServiceImpl implements ManageService {

	private static final ManageServiceImpl instance = new ManageServiceImpl();

	ManageServiceImpl() {
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

	@Override
	public List<Category_color> selectColor() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			List<Category_color> list = manageMapper.selectColor();
			if (list != null) {
				return list;
			}
		}
		return null;
	}

	@Override
	public List<Category_s> selectS() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			List<Category_s> list = manageMapper.selectS();
			if (list != null) {
				return list;
			}
		}
		return null;
	}

	@Override
	public List<Category_season> selectSeason() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			List<Category_season> list = manageMapper.selectSeason();

			if (list != null) {
				return list;
			}
		}
		return null;
	}

	@Override
	public List<Category_usage> selectUsage() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			List<Category_usage> list = manageMapper.selectUsage();

			if (list != null) {
				return list;
			}
		}
		return null;
	}

	@Override
	public int insertCloth(Cloth cloth) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertCloth(cloth);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}

	}

	@Override
	public int insertInventory(Cloth cloth) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertInventory(cloth);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;

		}
	}

	@Override
	public int insertCategorys1(int clothNum, Categorys categorys) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertCategorys1(clothNum, categorys);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int SelectLastPk() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			int num = manageMapper.SelectLastPk();
			if (num != 0) {
				return num;
			}
			return num;
		}
	}

	@Override
	public int insertUpdateCloth(int clothPk, int categoryPk) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

			int num = manageMapper.updateCloth(clothPk, categoryPk);
			if (num != 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int insertCategorys2(int clothNum, Categorys categorys) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertCategorys2(clothNum, categorys);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int insertCategorys3(int clothNum, Categorys categorys) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertCategorys3(clothNum, categorys);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int insertCategorys4(int clothNum, Categorys categorys) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.insertCategorys4(clothNum, categorys);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int updateInfoCloth(Cloth cloth) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.updateInfoCloth(cloth);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int updateInfoInventory(Cloth cloth) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.updateInfoInventory(cloth);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

	@Override
	public int logicalClothDelete(int cloth_num) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
			int num = manageMapper.logicalClothDelete(cloth_num);
			if (num > 0) {
				sqlSession.commit();
				return num;
			}
			return num;
		}
	}

}