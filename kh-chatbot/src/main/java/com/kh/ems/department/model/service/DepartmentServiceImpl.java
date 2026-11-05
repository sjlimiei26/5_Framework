package com.kh.ems.department.model.service;

import com.kh.ems.department.model.dto.DepartmentDTO;
import com.kh.ems.department.model.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentDTO> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public int getCount() {
        return departmentMapper.selectCount();
    }
}
