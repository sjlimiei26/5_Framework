package com.kh.ems.department.model.service;

import java.util.List;

import com.kh.ems.department.model.dto.DepartmentDTO;

public interface DepartmentService {
    List<DepartmentDTO> selectAll();

    int getCount();
}
