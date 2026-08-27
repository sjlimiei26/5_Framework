# 웹 소켓 (Web Socket)

## Web Socket ?
```
웹 소켓은 웹 브라우저와 서버 간에 지속적인 양방향 통신을 가능하게 해주는 기술입니다.
기존의 HTTP 통신처럼 요청/응답 방식이 아닌, 연결을 유지한 채로 양방향 데이터를 주고받을 수 있는 구조입니다.
```

### 역사
[Michael Carter](https://rakutentechnologyconference2017.sched.com/speaker/michael_carter.1x8q89tm)
<aside>
2008년, 마이클 카터와 이안 힉슨은 웹에서 실시간 통신을 위한 새로운 표준을 구상했습니다.

그렇게 탄생한 기술이 바로 Web Socket 입니다.

웹 소켓은 현재 수많은 디바이스에서 사용되고 있으며, 
실시간 채틴, 온라인 게임, 주식 시세 표기 등 다양한 분야에서 활용되고 있습니다.
</aside>


## HTTP vs WebSocket
[HTTP vs WebSocket](https://websocket.org/guides/road-to-websockets/)

| 항목 | HTTP | WebSocket |
| --- | --- | --- |
| 통신 방식 | 요청(Request)/응답(Response) 기반 | 양방향(Bidirectional) 통신 |
| 연결 방식 | 요청마다 새로운 연결 | 한 번 연결 후 유지 |
| 예시 | 웹 페이지 로딩, 이미지 요청 | 채팅, 실시간 알림, 게임 |
- **HTTP** 는 요청을 보내고 응답을 받으면 연결이 종료되는 구조
- **WebSocket** 은 한 번 연결되면 끊지 않고 양방향으로 자유롭게 데이터를 주고받는 구조

## 사용법
### 클라이언트(Client)
javascript 내장 객체인 `WebSocket` 을 사용하여 통신합니다.

이때 요청 주소는 http 프로토콜이 아닌 ws 프로토콜을 사용합니다. (보안이 필요한 경우 https 처럼 wss 를 사용)

소켓이 정상적으로 만들어지면 아래와 같은 **이벤트**를 사용할 수 있습니다.
- `open` : 연결이 정상적으로 되었을 때
- `message` : 데이터를 수신했을 때
- `error` : 오류가 발생했을 때
- `close` : 연결이 종료되었을 때

#### 연결 상태
- 0 : CONNECTING - 연결 시도 중
- 1 : OPEN - 연결 완료
- 2 : CLOSING - 연결 종료 중
- 3 : CLOSED - 연결 종료 또는 실패


### 서버 (Server)
웹 소켓 통신을 위한 별도의 설정을 추가해야 합니다.

_아래는 Spring Boot 기준 설정 항목들입니다._
#### dependency 추가
```xml
// Maven (pom.xml)
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>

// Gradle (build.gradle)
implementation 'org.springframework.boot:spring-boot-starter-websocket'
```

#### WebSocketHandler 추가
- 텍스트 기반의 통신을 지원하는 클래스 : `TextWebSocketHandler`
```java
@Component   // 빈 등록
public class SocketHandler extends TextWebSocketHandler {

	/**
	 * 소켓 연결 시 동작
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("클라이언트 연결 :: " + session.getId());
	}
	
	/**
	 * 메시지 수신 시 동작
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	  // 메시지 전송
	  String msg = message.getPayload();  // 수신된 메시지 추출
	  
	  session.sendMessage(new TextMessage(msg));  // 메시지 전송
	}	

	/**
	 * 소켓 종료 시 동작
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("클라이언트 종료 :: " + session.getId());	
	}

}
```

#### WebSocketConfig 설정 추가
- `WebSocketConfigurer` 인터페이스를 구현하여 설정 추가
```java
@Configuration           // 설정용 빈 등록
@EnableWebSocket         // 웹소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer {

	private final SocketHandler socketHandler;
	public WebSocketConfig(SocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler, 요청받을주소)
            .setAllowedOrigins(클라이언트origin);    // => 클라이언트 분리 시 origin 설정
            
/*
		registry.addHandler(socketHandler, "/chatting")
		        .setAllowedOrigins("http://localhost:5173");
		// 위와 같이 설정 시 localhost:5173 클라이언트에서
		// ws://localhost:8080/chatting 으로 요청 시 웹 소켓 통신이 가능함
*/            
	}

}
```

---

## References.
- [MDN WebSocket API](https://developer.mozilla.org/ko/docs/Web/API/WebSockets_API)
- [Spring Docs WebSocketHandler](https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/websocket.html#websocket-server-handler)
- [WebSocket.org](https://websocket.org/guides/road-to-websockets/)
- [Michael Carter](https://rakutentechnologyconference2017.sched.com/speaker/michael_carter.1x8q89tm)