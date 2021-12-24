package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.GennericResponse.DateTimeConstant;
import com.itsol.recruit_managerment.dto.JobRegisterDTO;
import com.itsol.recruit_managerment.dto.ResponseDto;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {

    @Autowired
    JobsRegisterRepositoryJpa jobsRegisterRepositoryJpa;

    @Autowired
    JobRegisterRepo jobRegisterRepo;

    public ResponseDto<JobsRegister> getAllJobsRegister(Integer pageNumber, Integer pageSite) {
        Pageable pageable = PageRequest.of(pageNumber, pageSite, Sort.by(Sort.Direction.ASC, "id"));

        Page<JobsRegister> jobPage = jobsRegisterRepositoryJpa.findAll(pageable);
        long totalRecord = jobPage.getTotalElements();
        List<JobsRegister> jobsRegisterList = jobPage.getContent();
        return new ResponseDto(totalRecord, jobsRegisterList);
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
            JobsRegister jobRegister = convert(jobRegisterDTO);
            jobsRegisterRepositoryJpa.save(jobRegister);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public JobsRegister convert(JobRegisterDTO jobRegisterDTO){
        try{
            JobsRegister jobsRegister = jobsRegisterRepositoryJpa.getById(Integer.parseInt(jobRegisterDTO.getId()));
            System.out.println(jobsRegister);
            if (jobRegisterDTO.getApplicantName().isEmpty()){
                jobsRegister.getUser().setFullName(jobRegisterDTO.getApplicantName());
            }
            if (jobRegisterDTO.getPositionName().isEmpty()){
                jobsRegister.getJobs().setJobPosition(jobRegisterDTO.getPositionName());
            }
            if (jobRegisterDTO.getJobRegisterStatus().isEmpty()){
                jobsRegister.getJobStatus().setStatusName(jobRegisterDTO.getJobRegisterStatus());
            }
            if(jobRegisterDTO.getApplicationTime().isEmpty()){
                SimpleDateFormat sdf = new SimpleDateFormat(DateTimeConstant.YYYYMMDD_FOMART);
                jobsRegister.setApplicationTime(sdf.parse(jobRegisterDTO.getApplicationTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



//    public List<JobsRegister> searchName(String fullName){
//       return jobsRegisterRepository.findByFullName(fullName);
//    }
//
//    public List<JobsRegister> searchVacancies(String vacancies){
//        return jobsRegisterRepository.findByVacancies(vacancies);
//    }
}
