package com.kh.ems.department.model.mapper;

import com.kh.ems.department.model.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<DepartmentDTO> selectAll();

    int selectCount();
}
