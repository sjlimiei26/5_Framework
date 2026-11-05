package com.kh.ems.employee.model.service;

import java.util.List;

import com.kh.ems.employee.model.dto.EmployeeDTO;

public interface EmployeeService {
    List<EmployeeDTO> selectAll();

    int getCount();
}
