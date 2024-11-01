package material;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import clothdetail.ClothReviewMapper;
import lombok.extern.slf4j.Slf4j;
import main.Mapper;
import management.ManageMapper;
import search.SoftSearchMapper;
import user.UserMapper;


@WebListener
@Slf4j
public class AppContextListener implements ServletContextListener {
	private static DataSource dataSource;
	private static SqlSessionFactory sessionFactory;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initDataSource();
		initSqlSessionFactory();
	}
	
	private void initSqlSessionFactory() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("dev", transactionFactory, dataSource);
		
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(Mapper.class);
		configuration.addMapper(UserMapper.class);
		configuration.addMapper(SoftSearchMapper.class);
		configuration.addMapper(ClothReviewMapper.class);
		configuration.addMapper(ManageMapper.class);
		sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

	private void initDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/lp");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		AppContextListener.dataSource = dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}
}





