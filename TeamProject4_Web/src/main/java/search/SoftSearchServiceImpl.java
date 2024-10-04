package search;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import material.AppContextListener;
import material.Cloth;

public class SoftSearchServiceImpl implements SoftSearchMapper {

	private static final SoftSearchServiceImpl instance = new SoftSearchServiceImpl();

	private SoftSearchServiceImpl() {}

	public static SoftSearchServiceImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Cloth> findAllCloth() {
		
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			SoftSearchMapper mapper = sqlSession.getMapper(SoftSearchMapper.class);
			List<Cloth> allCloth = mapper.findAllCloth();
			
			if (allCloth != null) {
				return allCloth;
			}
		}
		return null;
	}

}
