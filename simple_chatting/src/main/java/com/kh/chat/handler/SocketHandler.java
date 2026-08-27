package com.kh.chat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	// 웹소켓 세션 관리를 위한 맵
	Map<String, WebSocketSession> sessionMap = new HashMap<>();

	/** 소켓 연결 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		sessionMap.put(session.getId(), session);	// 맵에 세션 추가
	}

	/** 웹소켓을 통해 전달된 데이터 처리 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 전달된 메시지 추출
		String msg = message.getPayload();
		
		// 연결된 모든 세션에 추출한 메시지를 전송
		for (String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);			
			wss.sendMessage(new TextMessage(msg));			
		}	
	}
	
	/** 소켓 종료 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionMap.remove(session.getId());			// 맵에서 세션 제거
		
		super.afterConnectionClosed(session, status);
	}

	
}
