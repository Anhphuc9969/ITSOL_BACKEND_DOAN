package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.JobRegisterDTO;
import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface JobRegisterService {
    ResponseDTO<JobsRegister> getAllJobsRegister(Integer pageNumber, Integer pageSite);

    List<JobsRegister> search(JobRegisterSearchVm jobRegisterSearchVm);

    JobsRegister getJobsRegister(int id);

    JobsRegister updateJobsRegister(JobRegisterDTO jobRegisterDTO);

//    byte[] downloadCv(int applicantId) throws IOException;
    Resource downloadCv(int applicantId) throws IOException;

    String getCvFileName(String cvFilePath);

}
