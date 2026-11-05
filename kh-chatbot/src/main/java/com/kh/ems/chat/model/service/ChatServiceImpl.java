package com.kh.ems.chat.model.service;

import com.kh.ems.chat.tools.DepartmentTools;
import com.kh.ems.chat.tools.EmployeeTools;
import com.kh.ems.chat.tools.JobTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;
    private final EmployeeTools employeeTools;
    private final DepartmentTools departmentTools;
    private final JobTools jobTools;

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder,
            EmployeeTools employeeTools,
            DepartmentTools departmentTools,
            JobTools jobTools) {
        this.chatClient = chatClientBuilder.build();
        
        this.employeeTools = employeeTools;
        this.departmentTools = departmentTools;
        this.jobTools = jobTools;
    }

    @Override
    public String generateText(String question) {
        return this.chatClient.prompt()
                .system("""
                        당신은 사내 직원 관리 시스템(EMS)의 도우미입니다.
                        제공된 도구를 사용하여 직원, 부서, 직급에 관한 질문에 답변하세요.

                        만약 질문이 직원, 부서, 직급과 관련이 없거나 도구로 해결할 수 없는 내용이라면,
                        반드시 "해당 내용은 도움을 드리기 어렵습니다. 문의하기를 이용해주세요."라고만 답변하세요.
                        다른 부연 설명이나 추측은 하지 마세요.

                        모든 답변은 한국어로 정중하게 하세요.
                        """)
                .user(question)
                .tools(employeeTools, departmentTools, jobTools)
                .call()
                .content();
    }
}
