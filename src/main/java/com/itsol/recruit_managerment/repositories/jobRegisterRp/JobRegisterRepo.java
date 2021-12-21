package com.itsol.recruit_managerment.repositories.jobRegisterRp;

import com.itsol.recruit_managerment.dto.JobsRegisterDTO;

import java.util.List;

public interface JobRegisterRepo {
    List<JobsRegisterDTO> search(String searchField, String values);
}
