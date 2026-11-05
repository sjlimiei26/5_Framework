package com.kh.ems.job.controller;

import com.kh.ems.job.model.vo.Job;
import com.kh.ems.job.model.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Job> list = jobService.selectAll();
        model.addAttribute("list", list);
        return "job/list";
    }
}
