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
  <title>오픈 채팅방</title>
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
      <main class="chat-messages" id="chatMessages">
        <div class="system-message">${nickname}님이 채팅방에 입장하셨습니다.</div>
        <div class="message-wrapper bot">
          <span class="sender-name">채팅관리자</span>
          <div class="message-bubble">메시지를 입력해주세요!</div>
          <span class="message-time">방금 전</span>
        </div>
      </main>

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

</body>
</html>