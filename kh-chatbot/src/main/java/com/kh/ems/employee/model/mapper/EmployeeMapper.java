package com.kh.ems.employee.model.mapper;

import com.kh.ems.employee.model.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<EmployeeDTO> selectAll();

    int selectCount();
}
