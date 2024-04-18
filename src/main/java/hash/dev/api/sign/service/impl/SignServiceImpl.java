package hash.dev.api.sign.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hash.dev.api.sign.dao.SignDao;
import hash.dev.api.sign.service.SignService;

@Service("signService")
public class SignServiceImpl implements SignService {
	
	@Resource(name="signDao")
	private SignDao signDao;

	@Override
	public Map<String, Object> signInUser(Map<String, Object> map) throws Exception {
		String signInRst = "";
		String signInRstCode = "";
		Map<String, Object> signUserInfo = null;
		Map<String, Object> signRstMap = new HashMap<String, Object>();
		List<Map<String, Object>> logInRst = signDao.signIdCheck(map);
		if(logInRst.size() > 0) {
			if(map.get("userPw").equals(logInRst.get(0).get("userPw"))) {
				signInRst = "로그인 성공";
				signInRstCode = "SUCCESS";
				signUserInfo = logInRst.get(0);
			}else {
				signInRst = "입력하신 비밀번호를 확인해주세요.";
				signInRstCode = "ERROR";
			}
		}else {
			signInRst = "입력한 ID 정보와 일치하는 계정이 없습니다.";
			signInRstCode = "ERROR";
		}
		signRstMap.put("signInRst", signInRst);
		signRstMap.put("signInRstCode", signInRstCode);
		signRstMap.put("signUserInfo", signUserInfo);
		return signRstMap;
	}

}
