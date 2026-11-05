package com.kh.ems.employee.controller;

import com.kh.ems.employee.model.dto.EmployeeDTO;
import com.kh.ems.employee.model.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<EmployeeDTO> list = employeeService.selectAll();
        model.addAttribute("list", list);
        return "employee/list";
    }
}
