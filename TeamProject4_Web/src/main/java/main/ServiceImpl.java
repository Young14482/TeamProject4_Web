package main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import image.ClothImage;
import image.Image;
import material.AppContextListener;
import material.Cloth;

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

}
