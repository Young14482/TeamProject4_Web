package main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import image.Image;
import material.AppContextListener;

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

}
