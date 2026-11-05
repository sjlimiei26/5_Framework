package com.kh.ems.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ems.chat.model.dto.ChatMessage;
import com.kh.ems.chat.model.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chatPage() {
        return "chat/chat";
    }

    /*
     * 채팅 메시지를 전송하고 응답을 받는 엔드포인트
     */
    @PostMapping("/send")
    @ResponseBody
    public ChatMessage sendMessage(@RequestBody ChatMessage message) {
        String answer = chatService.generateText(message.getContent());
        return new ChatMessage("assistant", answer);
    }
}
