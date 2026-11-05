package com.kh.ems.main.controller;

import com.kh.ems.department.model.service.DepartmentService;
import com.kh.ems.employee.model.service.EmployeeService;
import com.kh.ems.job.model.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobService jobService;

    public StatController(EmployeeService employeeService,
            DepartmentService departmentService,
            JobService jobService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobService = jobService;
    }

    @GetMapping("/counts")
    public Map<String, Integer> getCounts() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("empCount", employeeService.getCount());
        stats.put("deptCount", departmentService.getCount());
        stats.put("jobCount", jobService.getCount());
        return stats;
    }
}
