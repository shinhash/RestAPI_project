package hash.dev.api.user.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hash.dev.api.user.dao.UserApiDao;
import hash.dev.api.user.service.UserApiService;

@Service("userApiService")
public class UserApiServiceImpl implements UserApiService {

	private static Logger logger = LoggerFactory.getLogger(UserApiServiceImpl.class);
	
	@Resource(name="userApiDao")
	private UserApiDao userApiDao;
			
	@Override
	public void saveUserInfo(Map<String, Object> map) throws Exception {
		logger.debug("saveUserInfo service impl");
		try {
			int insertRst = 0;
			String saveTeyp = (String) map.get("saveType");
			if(saveTeyp.equals("I")) {
				insertRst = userApiDao.insertUserInfo(map);
			}else if(saveTeyp.equals("U")) {
				insertRst = userApiDao.updateUserInfo(map);
			}
			
			if(insertRst <= 0) {
				throw new Exception("유저정보 저장 실패!!!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
