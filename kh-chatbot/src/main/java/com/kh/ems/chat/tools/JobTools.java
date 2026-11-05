package com.kh.ems.chat.tools;

import com.kh.ems.job.model.service.JobService;
import com.kh.ems.job.model.vo.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobTools {

    private static final Logger log = LoggerFactory.getLogger(JobTools.class);
    private final JobService jobService;

    public JobTools(JobService jobService) {
        this.jobService = jobService;
    }

    @Tool(description = "우리 회사의 모든 직급 정보를 상세히 조회합니다.")
    public List<Job> getAllJobs() {
        log.info("모든 직급 상세 정보 조회 요청");
        return jobService.selectAll();
    }

    @Tool(description = "우리 회사의 모든 직급 명칭 목록만 조회합니다. 질문에 직급명이 명확하지 않을 때 확인하기 위해 사용합니다.")
    public List<String> getJobNames() {
        log.info("모든 직급 명칭 목록 조회 요청");
        List<Job> list = jobService.selectAll();
        return list.stream()
                .map(Job::getJobName)
                .toList();
    }

    @Tool(description = "특정 명칭이 포함된 직급의 상세 정보를 검색합니다.")
    public List<Job> searchJob(
            @ToolParam(description = "검색할 직급 명칭 (예: '대리', '부장')") String name) {
        log.info("직급 검색 요청 - 명칭: {}", name);
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }

        List<Job> allJobs = jobService.selectAll();
        return allJobs.stream()
                .filter(j -> j.getJobName().contains(name))
                .toList();
    }

    @Tool(description = "현재 회사에 등록된 총 직급 종류의 개수를 조회합니다.")
    public int getJobCount() {
        log.info("총 직급 개수 조회 요청");
        return jobService.getCount();
    }
}
