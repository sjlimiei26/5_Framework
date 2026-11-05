package com.kh.ems.job.model.mapper;

import com.kh.ems.job.model.vo.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobMapper {
    List<Job> selectAll();

    int selectCount();
}
