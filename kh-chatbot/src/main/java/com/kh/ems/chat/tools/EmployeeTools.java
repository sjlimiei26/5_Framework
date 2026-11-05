package com.kh.ems.chat.tools;

import com.kh.ems.employee.model.dto.EmployeeDTO;
import com.kh.ems.employee.model.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeTools {

    private static final Logger log = LoggerFactory.getLogger(EmployeeTools.class);
    private final EmployeeService employeeService;

    public EmployeeTools(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Tool(description = "우리 회사의 모든 직원 정보를 상세히 조회합니다.")
    public List<EmployeeDTO> getAllEmployees() {
        log.info("모든 직원 상세 정보 조회 요청");
        return employeeService.selectAll();
    }

    @Tool(description = "우리 회사의 모든 직원 이름 목록만 조회합니다. 질문에 직원명이 명확하지 않을 때 이름을 확인하기 위해 사용합니다.")
    public List<String> getEmployeeNames() {
        log.info("모든 직원 이름 목록 조회 요청");
        List<EmployeeDTO> list = employeeService.selectAll();
        return list.stream()
                .map(EmployeeDTO::getEmpName)
                .toList();
    }

    @Tool(description = "특정 이름을 가진 직원의 상세 정보를 검색합니다.")
    public List<EmployeeDTO> searchEmployee(
            @ToolParam(description = "검색할 직원의 이름 (예: '김철수', '이영희')") String name) {
        log.info("직원 이름 검색 요청 - 이름: {}", name);
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }

        List<EmployeeDTO> allEmployees = employeeService.selectAll();
        return allEmployees.stream()
                .filter(e -> e.getEmpName().contains(name))
                .toList();
    }

    @Tool(description = "현재 회사의 총 직원 수를 조회합니다.")
    public int getEmployeeCount() {
        log.info("총 직원 수 조회 요청");
        return employeeService.getCount();
    }
}
