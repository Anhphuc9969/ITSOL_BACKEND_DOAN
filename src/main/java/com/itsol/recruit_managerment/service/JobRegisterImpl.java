package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDto;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.repositories.JobsRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRegisterImpl {

    @Autowired
    JobsRegisterRepository jobsRegisterRepository;

    public ResponseDto getAllJobsRegister(Integer pageNumber, Integer pageSite) {
        Pageable pageable = PageRequest.of(pageNumber, pageSite, Sort.by(Sort.Direction.ASC, "applicationTime"));

        Page<JobsRegister> jobPage = jobsRegisterRepository.findAll(pageable);
        long totalRecord = jobPage.getTotalElements();
        List<JobsRegister> jobsRegisterList = jobPage.getContent();
        return new ResponseDto(totalRecord, jobsRegisterList);
    }

    public JobsRegister getJobsRegister(int id){
        return jobsRegisterRepository.findById(id);
    }
}
