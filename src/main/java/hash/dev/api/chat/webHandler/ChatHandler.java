package hash.dev.api.chat.webHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ChatHandler.class);

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		logger.debug(session.getId() + " 님이 입장하셨습니다.");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		logger.debug(session.getAttributes() + "");
		logger.debug(session.getId() + " : " + message);
		
		logger.debug("session : " + session);
		logger.debug("message : " + message);
		logger.debug("payLoad : " + message.getPayload());
		
		Map<String, Object> signUserInfo = (Map<String, Object>) session.getAttributes().get("signUserInfo");
		
		String userId = (String) signUserInfo.get("userId");
		
		for(WebSocketSession se : sessionList) {
			se.sendMessage(new TextMessage(userId + "::" + message.getPayload()));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.debug(session.getId() + " 님이 퇴장하셨습니다.");
	}
}
