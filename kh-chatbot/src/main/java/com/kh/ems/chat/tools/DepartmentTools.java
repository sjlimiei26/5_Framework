package com.kh.ems.chat.tools;

import com.kh.ems.department.model.dto.DepartmentDTO;
import com.kh.ems.department.model.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentTools {

    private static final Logger log = LoggerFactory.getLogger(DepartmentTools.class);
    private final DepartmentService departmentService;

    public DepartmentTools(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Tool(description = "우리 회사의 모든 부서 정보를 상세히 조회합니다.")
    public List<DepartmentDTO> getAllDepartments() {
        log.info("모든 부서 상세 정보 조회 요청");
        return departmentService.selectAll();
    }

    @Tool(description = "우리 회사의 모든 부서 이름(명칭) 목록만 조회합니다. 질문에 부서명이 명확하지 않을 때 부서군을 확인하기 위해 사용합니다.")
    public List<String> getDepartmentNames() {
        log.info("모든 부서 이름 목록 조회 요청");
        List<DepartmentDTO> list = departmentService.selectAll();
        return list.stream()
                .map(DepartmentDTO::getDeptTitle)
                .toList();
    }

    @Tool(description = "특정 키워드가 포함된 부서의 상세 정보를 검색합니다.")
    public List<DepartmentDTO> searchDepartment(
            @ToolParam(description = "검색할 부서 명칭의 키워드 (예: '영업', '인사')") String keyword) {
        log.info("부서 검색 요청 - 키워드: {}", keyword);
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }

        List<DepartmentDTO> allDepts = departmentService.selectAll();
        return allDepts.stream()
                .filter(d -> d.getDeptTitle().contains(keyword))
                .toList();
    }

    @Tool(description = "현재 회사의 총 부서 개수를 조회합니다.")
    public int getDepartmentCount() {
        log.info("총 부서 개수 조회 요청");
        return departmentService.getCount();
    }
}
