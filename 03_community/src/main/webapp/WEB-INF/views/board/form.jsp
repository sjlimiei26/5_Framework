<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <h2 class="page-title">게시글 ${mode == 'edit' ? '수정' : '작성'}</h2>
    
    <form action="${mode == 'edit' ? '/board/edit/'.concat(board.boardId) : '/board/write'}" 
		  method="post" enctype="multipart/form-data"
          class="form form-flex">
          <div class="form-row">
              <label for="category">카테고리</label>
              <select id="category" name="category">
                  <option value="자유" ${board.category == '자유' ? 'selected' : '' }>자유</option>
                  <option value="질문" ${board.category == '질문' ? 'selected' : '' }>질문</option>
                  <option value="공지" ${board.category == '공지' ? 'selected' : '' }>공지</option>
              </select>
          </div>
          
          <div class="form-row">
              <label for="title">제목</label>
              <input type="text" id="title" name="title" value="${board.title}" required>
          </div>

          <div class="form-row">
              <label for="content">내용</label>
              <textarea id="content" name="content" rows="10" required>${board.content}</textarea>
          </div>    

		  <c:if test="${mode == 'edit' and not empty board.images}">
		      <div class="form-row">
		          <ul class="board-image-list">
		              <c:forEach var="img" items="${board.images}">
		                  <li><img src="${img.imagePath}" alt="${img.originalName}"> </li>
		              </c:forEach>
		          </ul>
				  <p class="form-tip-error">※ 새로운 이미지를 추가할 경우, 기존 이미지는 삭제됩니다.</p>
		      </div>
		  </c:if>
		            
          <div class="form-row">
              <label for="images">첨부 이미지(여러장 가능)</label>
              <%--
                  multiple -> 사용자가 파일 선택창에서 여러장을 한번에 고를 수 있다.
              --%>
              <input type="file" id="images" name="imageFiles" accept="image/*" multiple>
              <div id="image-preview-list" class="board-image-list"></div>
          </div>

          <div class="form-row">
              <button type="submit" class="btn btn-primary">${mode == 'edit' ? '수정완료' : '등록'}</button>
          </div>                        
    </form>
<script src="/js/board.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />