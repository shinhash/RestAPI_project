package hash.dev.api.sign.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import hash.dev.api.sign.service.SignService;

@CrossOrigin
@Controller
public class SignController {
	
	private static Logger logger = LoggerFactory.getLogger(SignController.class);
	
	@Resource(name="signService")
	private SignService signService;

	@RequestMapping(value="/login/user")
	public String signInUser(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", request.getParameter("userId"));
		map.put("userPw", request.getParameter("userPw"));
		
		Map<String, Object> signInRst = signService.signInUser(map);
		if(signInRst.get("signInRstCode").equals("SUCCESS")) {
			String signUserSessionId = session.getId();
			session.setAttribute("signUserInfo", signInRst.get("signUserInfo"));
			session.setAttribute("signUserSessionId", signUserSessionId);
			model.addAttribute("signUserSessionId", signUserSessionId);
			logger.debug("logInSessionId : [ " + session.getId() + " ]");
			// 알림창 기능용 유저세션리스트
			signUserSessionListUpdate(session, request);
		}
		signInRst.remove("signUserInfo");
		model.addAttribute("signInRst", signInRst);
		return "ajaxJasonView";
	}
	
	public void signUserSessionListUpdate(HttpSession session, HttpServletRequest request) {
		ServletContext application = request.getServletContext();
	}
}
