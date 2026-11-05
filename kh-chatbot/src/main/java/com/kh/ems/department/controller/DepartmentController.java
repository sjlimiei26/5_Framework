package com.kh.ems.department.controller;

import com.kh.ems.department.model.dto.DepartmentDTO;
import com.kh.ems.department.model.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<DepartmentDTO> list = departmentService.selectAll();
        model.addAttribute("list", list);
        return "department/list";
    }
}
