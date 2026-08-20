package com.kh.community.member.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequest {
	
	private String memberId;
	private String memberName;
	private String nickname;
	private String email;
	private MultipartFile profileImage;
	
	private String profile;

}
