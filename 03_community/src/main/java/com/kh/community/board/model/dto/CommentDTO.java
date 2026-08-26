package com.kh.community.board.model.dto;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("CommentDTO")
public class CommentDTO {
	// TB_COMMENT 기준으로 필드 정의
	private Long commentId;
	private Long boardId;
	private String memberId;
	private String content;
	private LocalDateTime createAt;
	
	private String writerNickname;		// 작성자 닉네임
	private String createAtStr;
}
