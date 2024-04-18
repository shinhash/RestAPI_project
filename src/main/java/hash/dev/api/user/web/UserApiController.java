package hash.dev.api.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import hash.dev.api.user.service.UserApiService;

@CrossOrigin
@Controller
public class UserApiController {

	private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
	
	@Resource(name="userApiService")
	private UserApiService userApiService;
	
	@RequestMapping(value="/select/user")
	public String selectChatUserList(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		return "ajaxJasonView";
	}
	
	@RequestMapping(value="/save/user")
	public String saveChatUser(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userNm = request.getParameter("userNm");
		String saveType = request.getParameter("saveType");
		
		// vo 작성 필요
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		map.put("userNm", userNm);
		map.put("saveType", saveType);
		
		logger.debug("===================================================== map info =====================================================");
		logger.debug(map.toString());
		logger.debug("====================================================================================================================");
		userApiService.saveUserInfo(map);
		return "ajaxJasonView";
	}
}
