package hash.dev;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={  "classpath:hash/dev/config/spring/context-common.xml", 
								   "classpath:hash/dev/config/spring/context-datasource.xml",
								   "classpath:hash/dev/config/spring/context-mapper.xml",
								   "classpath:hash/dev/config/spring/context-transaction.xml"})
@WebAppConfiguration
public class webTestConfig {
	
	private static Logger logger = LoggerFactory.getLogger(webTestConfig.class);
	
//	public static final String DRIVER = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
//	public static final String URL = "log4jdbc:mysql://localhost:3306/iron_drum";
	public static final String URL = "jdbc:mysql://localhost:3306/iron_drum";
	public static final String USER = "hash";
	public static final String PW = "1234";
	
	@Autowired
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, dataSource);
	}

	@Ignore
	@Test
	public void test() throws Exception {
		
		Class.forName(DRIVER);
		Connection con = null;
		try{
			con = DriverManager.getConnection(URL, USER, PW);
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
	}

}
