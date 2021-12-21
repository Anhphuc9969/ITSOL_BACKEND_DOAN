package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.JobsRegisterDTO;
import com.itsol.recruit_managerment.model.JobsRegister;

import java.util.List;

public interface JobRegisterService {

    List<JobsRegisterDTO> search(String searchField, String value);

    JobsRegister getJobsRegister(int id);

}
