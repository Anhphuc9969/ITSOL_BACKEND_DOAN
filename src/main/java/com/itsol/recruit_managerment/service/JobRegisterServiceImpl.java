package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.JobRegisterDTO;
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
    public List<JobsRegister> search(JobRegisterSearchVm jobRegisterSearchVm) {
        List<JobsRegister> jobsRegister = jobRegisterRepo.search(jobRegisterSearchVm);
        return jobsRegister;
    }

    @Override
    public JobsRegister getJobsRegister(int id) {
        return jobsRegisterRepositoryJpa.findById(id);
    }

    @Override
    public Boolean updateJobsRegister(JobRegisterDTO jobRegisterDTO) {
        try{
            JobsRegister jobsRegister = jobsRegisterRepositoryJpa.getById(jobRegisterDTO.getId());
            jobsRegisterRepositoryJpa.save(jobsRegister);
            System.out.println(jobsRegister);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public JobsRegister updateStatusName(JobsRegister jobsRegister) {
        return jobsRegisterRepositoryJpa.save(jobsRegister);
    }

//    public JobsRegister convert(JobRegisterDTO jobRegisterDTO){
//        try{
//            JobsRegister jobsRegister = jobsRegisterRepositoryJpa.getById(jobRegisterDTO.getId());
//            System.out.println(jobsRegister);
//            if (jobRegisterDTO.getJobRegisterStatusId() != '' && !jobRegisterDTO.getApplicantName().getFullName().isEmpty()){
//                jobsRegister.getUser().setFullName(jobRegisterDTO.getApplicantName().getFullName());
//            }
////            if (jobRegisterDTO.getPositionName().getJobPosition()!= null && !jobRegisterDTO.getPositionName().getJobPosition().isEmpty()){
////                jobsRegister.getJobs().setJobPosition(jobRegisterDTO.getPositionName().getJobPosition());
////            }
////            if (jobRegisterDTO.getJobRegisterStatus().getStatusName() != null && !jobRegisterDTO.getJobRegisterStatus().getStatusName().isEmpty()){
////                jobsRegister.getJobStatus().setStatusName(jobRegisterDTO.getJobRegisterStatus().getStatusName());
////            }
////            if (jobRegisterDTO.getReason() != null && !jobRegisterDTO.getReason().isEmpty()){
////                jobsRegister.getJobStatus().setStatusName(jobRegisterDTO.getJobRegisterStatus().getStatusName());
////            }
//            return jobsRegister;
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }


}
