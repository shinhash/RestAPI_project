package hash.dev.main;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hash.dev.webTestConfig;

public class MainTest extends webTestConfig {
	
//	private static Logger logger = LoggerFactory.getLogger(MainTest.class);
	
	@Resource(name="mainMapperDao")
//	private mainMapperDao mainMapperDao;
	
	@Test
	public void dbconnectTest() throws Exception {
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
	
	@Test
	public void mainControllerTest() throws Exception {
		mockMvc.perform(post("/main/user"))
		.andDo(print())
		.andReturn();
	}
	
	@Test
	public void selectUserListServiceTest() throws Exception {
		/***Given***/
		List<Map<String, Object>> userList = null;

		/***When***/
//		userList = mainMapperDao.selectUserList(null);

		/***Then***/
//		logger.debug("======================================================userList.toString : " + userList.toString());
		assertEquals("hash", userList.get(0).get("userId"));
	}

}
