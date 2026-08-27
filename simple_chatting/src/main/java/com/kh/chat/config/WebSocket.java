package com.kh.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kh.chat.handler.SocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocket implements WebSocketConfigurer {
	
	private final SocketHandler socketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 웹소켓 통신을 위한 주소 지정 : "/chat"
		// 해당 주소로 요청이 들어오는 경우 SocketHandler로 처리
		registry.addHandler(socketHandler, "/chat");
		
	}

	
}
