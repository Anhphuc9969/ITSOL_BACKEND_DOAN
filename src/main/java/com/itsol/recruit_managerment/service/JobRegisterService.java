package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;

import java.util.List;

public interface JobRegisterService {
    ResponseDTO<JobsRegister> getAllJobsRegister(Integer pageNumber, Integer pageSite);

    List<JobRegisterSearchVm> search(JobRegisterSearchVm jobRegisterSearchVm);

    JobsRegister getJobsRegister(int id);

}
