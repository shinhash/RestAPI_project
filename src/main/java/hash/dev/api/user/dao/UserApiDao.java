package hash.dev.api.user.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userApiDao")
public class UserApiDao {
	
	@Autowired
	private SqlSessionTemplate session;

	public int insertUserInfo(Map<String, Object> map) {
		return session.insert("userApiDao.insertUserInfo", map);
	}

	public int updateUserInfo(Map<String, Object> map) {
		return session.update("userApiDao.updateUserInfo", map);
	}

}
