package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.repositories.jobRegisterRp.JobRegisterRepo;
import com.itsol.recruit_managerment.repositories.jobRegisterRp.JobsRegisterRepositoryJpa;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {

    @Autowired
    JobsRegisterRepositoryJpa jobsRegisterRepositoryJpa;

    @Autowired
    JobRegisterRepo jobRegisterRepo;

    public ResponseDTO<JobsRegister> getAllJobsRegister(Integer pageNumber, Integer pageSite) {
        Pageable pageable = PageRequest.of(pageNumber, pageSite, Sort.by(Sort.Direction.ASC, "id"));

        Page<JobsRegister> jobPage = jobsRegisterRepositoryJpa.findAll(pageable);
        long totalRecord = jobPage.getTotalElements();
        List<JobsRegister> jobsRegisterList = jobPage.getContent();
        return new ResponseDTO(totalRecord, jobsRegisterList);
    }

    @Override
    public List<JobRegisterSearchVm> search(JobRegisterSearchVm jobRegisterSearchVm) {
        List<JobRegisterSearchVm> jobsRegisterDTOList = jobRegisterRepo.search(jobRegisterSearchVm);
        return jobsRegisterDTOList;
    }

    @Override
    public JobsRegister getJobsRegister(int id) {
        return jobsRegisterRepositoryJpa.findById(id);
    }

//    public List<JobsRegister> searchName(String fullName){
//       return jobsRegisterRepository.findByFullName(fullName);
//    }
//
//    public List<JobsRegister> searchVacancies(String vacancies){
//        return jobsRegisterRepository.findByVacancies(vacancies);
//    }
}
