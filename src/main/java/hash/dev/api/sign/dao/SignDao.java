package hash.dev.api.sign.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("signDao")
public class SignDao {
	
	@Autowired
	private SqlSessionTemplate session;

	public List<Map<String, Object>> signIdCheck(Map<String, Object> map) {
		return session.selectList("signDao.signIdCheck", map);
	}

	
}
