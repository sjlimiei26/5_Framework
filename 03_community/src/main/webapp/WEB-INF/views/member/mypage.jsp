<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	<h2 class="page-title">마이페이지</h2>
	
	<div class="mypage-card">
		
		<c:choose>
			<c:when test="${not empty loginMember.profile}">
				<img id="profile-preview" class="profile-preview" src="${loginMember.profile}" alt="프로필 이미지" />
			</c:when>			
			<c:otherwise>
				<div id="profile-preview-placeholder" class="profile-preview profile-preview-placeholder">사진없음</div>
				<img id="profile-preview" class="profile-preview" alt="프로필 이미지" style="display:none;" />
			</c:otherwise>
		</c:choose>
		<input type="hidden" id="profile" name="profile" value="${loginMember.profile}"/> 
		<input type="file" id="profile-image" name="profileImage" accept="image/*" style="display:none;">
		<%--
			* <dl> : 이름-값 형태로 정보를 묶어주는 전체 목록 
			* <dt> : 정보의 제목/이름
			* <dd> : 제목에 대한 상세 값/내용
		--%>
		<dl class="mypage-info">
			<dt>아이디</dt>
			<dd>${loginMember.memberId}</dd>
			
			<dt>이름</dt>
			<dd>
				<input id="member-name" name="memberName" type="text" value="${loginMember.memberName}" readonly />
			</dd>
			
			<dt>닉네임</dt>
			<dd>
				<input id="nickname" name="nickname" type="text" value="${loginMember.nickname}" readonly />
			</dd>
			
			<dt>이메일</dt>
			<dd>
				<input id="email" name="email" type="text" value="${loginMember.email}" readonly />
			</dd>
			
			<dt>가입일</dt>
			<dd>${loginMember.createAtStr}</dd>
		</dl>
		
		
	</div>
	
	
	<form action="/member/withdraw" method="post" class="form form-flex form-row-center"
	      onsubmit="return confirm('정말 탈퇴하시겠습니까?')">
		<button type="button" id="update-btn" class="btn btn-primary" style="display:none;">수정</button>
		<button class="btn btn-danger">회원 탈퇴</button>
	</form>

<script src="/js/member.js"></script>	
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>











