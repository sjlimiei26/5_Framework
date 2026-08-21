package com.kh.community.board.model.dto;

import lombok.Getter;
import lombok.Setter;

// 게시판 검색 조건을 저장하기 위한 클래스
@Getter
@Setter
public class BoardSearchCondition {
	// 검색 파라미터 : 카테고리, 검색 종류, 키워드
	private String category;	// 전체, 자유, 공지, 질문
	private String searchType;	// titleContent, title, content, writer
	private String keyword;		// 검색어. 입력 값이 없으면 null
}
