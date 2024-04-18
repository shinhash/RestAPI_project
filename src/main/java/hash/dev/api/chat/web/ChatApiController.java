package hash.dev.api.chat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
public class ChatApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatApiController.class);

	@RequestMapping(value="/select/roomList")
	public String selectChatRoomList(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		return "ajaxJasonView";
	}
	
	@RequestMapping(value="/select/contList")
	public String selectChatContList(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		return "ajaxJasonView";
	}
	
	@RequestMapping(value="/save/roomInfo")
	public String saveChatRoomInfo(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		return "ajaxJasonView";
	}
	
	@RequestMapping(value="/save/contInfo")
	public String saveChatContInfo(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		return "ajaxJasonView";
	}
}
