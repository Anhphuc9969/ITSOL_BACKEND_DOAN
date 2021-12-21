package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDto;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;

import java.util.List;

public interface JobRegisterService {
    ResponseDto<JobsRegister> getAllJobsRegister(Integer pageNumber, Integer pageSite);

    List<JobRegisterSearchVm> search(JobRegisterSearchVm jobRegisterSearchVm);

    JobsRegister getJobsRegister(int id);

}
