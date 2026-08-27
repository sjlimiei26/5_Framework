package com.kh.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/enter")
	public String enterChatting(String nickname, HttpSession session) {
		
		session.setAttribute("nickname", nickname);
		
		return "chat/room";
	}
}
