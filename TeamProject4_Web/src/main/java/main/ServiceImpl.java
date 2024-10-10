package main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import image.ClothImage;
import image.Image;
import material.AppContextListener;
import material.Cloth;
import shoppingCart.ShoppingCartItem;

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

	@Override
	public int insertPayment(ShoppingCartItem order) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			int result = mapper.insertPayment(order);

			if (result == 1) {
				sqlSession.commit();
				return result;
			}
		}
		return 0;
	}
}
