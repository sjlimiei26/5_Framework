<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <div class="nickname-card">
      <div class="card-icon">💬</div>
      <h1 class="card-title">채팅방 입장</h1>
      <p class="card-subtitle">사용할 닉네임을 입력하고 입장해주세요.</p>
      
      <form action="/enter" method="post" class="nickname-form">
        <input 
          type="text" 
          name="nickname" 
          placeholder="닉네임 (2~10자)" 
          minlength="2" 
          maxlength="10" 
          autocomplete="off" 
          required 
          autofocus
        />
        <button type="submit" class="primary-btn">입장하기</button>
      </form>
    </div>
  </div>

</body>
</html>