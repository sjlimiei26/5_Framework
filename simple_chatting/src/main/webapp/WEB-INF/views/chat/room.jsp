<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // 세션에 닉네임이 없는 비정상 접근 시 로그인 페이지로 리다이렉트
  if (session.getAttribute("nickname") == null) {
      response.sendRedirect(request.getContextPath() + "/chat/login");
      return;
  }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Simple Chatting</title>
  <link rel="stylesheet" href="/css/chat.css">
</head>
<body>

  <div class="screen-container">
    <div class="chat-container">
      
      <!-- 헤더 -->
      <header class="chat-header">
        <div class="header-left">
          <span class="status-dot"></span>
          <div>
            <h2 class="room-title">오픈 채팅방</h2>
            <span class="user-badge">접속자: <strong>${nickname}</strong></span>
          </div>
        </div>
      </header>

      <!-- 메시지 목록 영역 -->
      <main class="chat-messages" id="chatMessages"></main>

      <!-- 메시지 입력 폼 -->
      <form class="chat-input-area" id="chatForm">
        <input 
          type="text" 
          class="chat-input" 
          id="messageInput" 
          placeholder="메시지를 입력하세요..." 
          autocomplete="off" 
          required 
        />
        <button type="submit" class="send-btn">전송</button>
      </form>

    </div>
  </div>

  <script>
	// 세션에 저장된 사용자 닉네임을 전역 변수로 저장 (외부 자바스크립트 파일에서 사용)
    const CURRENT_USER = "${sessionScope.nickname}";
  </script>
  <script src="/js/chat.js"></script>
</body>
</html>