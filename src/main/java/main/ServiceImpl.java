package main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import clothdetail.Review;
import image.ClothImage;
import image.Image;
import material.AppContextListener;
import material.Cloth;
import payment.ClothSize;
import payment.PayCloth;
import payment.PaymentInfo;
import shoppingCart.ShoppingCartItem;
import user.User;

public class ServiceImpl implements Service {

	private static final ServiceImpl instance = new ServiceImpl();

	private ServiceImpl() {
	}

	public static ServiceImpl getInstance() {

		return instance;
	}

	@Override
	public boolean insertImage(String imageName, String base64Str) {

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int pk = mapper.insertImage(imageName, base64Str);

			if (pk == 1) {
				sqlSession.commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Image> findAllImage() {

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<Image> allImage = mapper.findAllImage();

			if (allImage != null) {
				return allImage;
			}
		}
		return null;
	}

	@Override
	public List<Cloth> findAllCloth() {

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<Cloth> allCloth = mapper.findAllCloth();

			if (allCloth != null) {
				return allCloth;
			}
		}

		return null;
	}

	public List<ClothImage> findAllClothImage() {

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<ClothImage> clothImgList = mapper.findAllClothImage();

			if (clothImgList != null) {
				return clothImgList;
			}
		}

		return null;
	}

	public boolean insertImageDetail(String base64Image, int cloth_num) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.insertClothDetailImg(base64Image, cloth_num);

			if (result == 1) {
				sqlSession.commit();
				return true;
			}
		}
		return false;
	}


	@Override
	public List<Cloth> findSearchCloth(String gender, int parsedSeason, int parsedColor, int parsedUsage,
			int parsedMinPrice, int parsedMaxPrice) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<Cloth> cloth = mapper.findSearchCloth(gender, parsedSeason, parsedColor, parsedUsage, parsedMinPrice,
					parsedMaxPrice);

			if (cloth != null) {
				return cloth;
			}
		}
		return null;
	}

	@Override
	public List<ShoppingCartItem> selectShoppingCart(String userId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<ShoppingCartItem> list = mapper.selectShoppingCart(userId);

			if (list != null) {
				return list;
			}
		}
		return null;
	}

	public int deleteFromShoppingCart(int clothNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.deleteFromShoppingCart(clothNum);
			if (result != 0) {
				sqlSession.commit(); // 커밋
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean insertImageToImg(String imageName, String base64Str) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.insertImageToImg(imageName, base64Str);

			if (result == 1) {
				sqlSession.commit();
				return true;
			}
		}
		return false;
	}
	
	public boolean addToClothInShoppingCart(String userId, int cloth_num) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			Integer result = mapper.findUserShoppingCartDetail(userId, cloth_num);
			if (result != null) {
				int updateResult = mapper.updateUserShoppingCartDetail((result+1), userId, cloth_num);
				if (updateResult == 1) {
					sqlSession.commit();
					return true;
				}
				
			} else {
				int insertResult = mapper.insertUserShoppingCartDetail(userId, cloth_num);
				if (insertResult == 1) {
					sqlSession.commit();
					return true;
				}
			}
		}
		return false;
	}

	public List<PayCloth> findUserClothsToPay(String userId) {
		
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			List<PayCloth> clothList = mapper.findUserClothsToPay(userId);
			return clothList;
		}
		
	}

	public boolean insertReview(Review review) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.insertReview(review.getUser_ID(), review.getCloth_num(), review.getReviewDetail(), review.getGood_or_bad());
			if (result == 1) {
				mapper.updateUserStatus(review.getUser_ID(), review.getCloth_num());
				if (review.getGood_or_bad().equals("good")) {
					mapper.updateClothGood(review.getCloth_num());
				} else if (review.getGood_or_bad().equals("bad")) {
					mapper.updateClothBad(review.getCloth_num());
				}
					
				sqlSession.commit();
				return true;
			}
		}
		
		return false;
	}
	

	@Override
	public int insertPayment(ShoppingCartItem order, int cloth_size) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.insertPayment(order.getUser_Id(), order.getCloth_num(), order.getShoppingcart_count(), cloth_size);

			if (result == 1) {
				sqlSession.commit();
				return result;
			}
		}
		return 0;
	}

	public int lastInsertClothNum() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.ClothLastNum();
			return result;
		}
		
	}

	public boolean cancelOrder(int cloth_num, String userId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			PaymentInfo paymentInfo = mapper.findUserPayPrice(userId, cloth_num);
			mapper.cancelOrder(userId, cloth_num);
			mapper.changeUserUseMoneyToDown(userId, paymentInfo.getTotalPayPrice());
			mapper.changeClothSoldToDown(paymentInfo.getPaymentCount(), cloth_num);
			
			sqlSession.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User getUserInfo(String userId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			
			User user = mapper.getUser(userId);
			return user;
		}
	}
	// 사용금액 확인
	@Override
	public int userUseMoney(String userId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			
			int useMoney = mapper.getUseMoney(userId);
			return useMoney;
		}
	}
	// 사용금액 업데이트
	@Override
	public void updateUseMoney(String userId, int useMoney) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			mapper.updateUseMoney(userId, useMoney);
			sqlSession.commit();
		}
	}

	@Override
	public int getClothSold(int clothNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			
			int clothSold = mapper.getClothSold(clothNum);
			return clothSold;
		}
	}

	@Override
	public void updateClothSold(int clothNum, int count) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			mapper.updateClothSold(clothNum,count);
			sqlSession.commit();
		}
	}

	public ClothSize findClothSize(int chooseClothNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			ClothSize clothSize = mapper.findClothSize(chooseClothNum);
			return clothSize;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
