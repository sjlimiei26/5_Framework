package com.kh.ems.job.model.service;

import com.kh.ems.job.model.vo.Job;
import com.kh.ems.job.model.mapper.JobMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;

    public JobServiceImpl(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public List<Job> selectAll() {
        return jobMapper.selectAll();
    }

    @Override
    public int getCount() {
        return jobMapper.selectCount();
    }
}
