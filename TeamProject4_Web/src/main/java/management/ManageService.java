package management;

import java.util.List;

public interface ManageService {

	JoinUser updateUserGrade(JoinUser joinUser);

	JoinUser updateUserBlock(JoinUser joinUser);

	// 컬러 카테고리 다들고 오기
	List<Category_color> selectColor();

	// s 카테고리 다들고 오기
	List<Category_s> selectS();

	// 시즌 카테고리 다들고 오기
	List<Category_season> selectSeason();

	// usage 카테고리 다들고 오기
	List<Category_usage> selectUsage();

	// 옷 테이블 삽임
	int insertCloth(Cloth cloth);

	// 옷 수량 삽입
	int insertInventory(Cloth cloth);

	// 카테고리 삽입
	int insertCategorys1(int clothNum, Categorys categorys);

	int insertCategorys2(int clothNum, Categorys categorys);

	int insertCategorys3(int clothNum, Categorys categorys);

	int insertCategorys4(int clothNum, Categorys categorys);

	// 카테고리 삽입하면 pk값 넣기
	int SelectLastPk();

	// 카테고리 만들어 질때 옷 테이블의 카테고리 수정
	int updateCloth(int clothPk, int categoryPk);
}