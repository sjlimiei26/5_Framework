package com.kh.ems.employee.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.ems.employee.model.dto.EmployeeDTO;
import com.kh.ems.employee.model.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int getCount() {
        return employeeMapper.selectCount();
    }
}
