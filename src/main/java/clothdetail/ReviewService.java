package clothdetail;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import material.AppContextListener;

public class ReviewService {

	private static final ReviewService instance = new ReviewService();

	private ReviewService() {
	}

	public static ReviewService getInstance() {
		return instance;
	}
	
	public List<Review> findReview(int cloth_num) {

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			ClothReviewMapper mapper = sqlSession.getMapper(ClothReviewMapper.class);
			List<Review> reviewList = mapper.findReview(cloth_num);
			if (reviewList != null) {
				return reviewList;
			}
		}
		return null;
	}
	
}
