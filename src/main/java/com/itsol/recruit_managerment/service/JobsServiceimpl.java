package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDto;
import com.itsol.recruit_managerment.model.Jobs;
import com.itsol.recruit_managerment.repositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsServiceimpl {

    @Autowired
    JobRepo jobRepo;

    public Jobs getFindByIdJob(Long id) {
        Optional<Jobs> jobRepoById = jobRepo.findById(id);
        return jobRepoById.orElse(null);
    }

    public List<Jobs> getAllJob() {
        List<Jobs> list = jobRepo.findAll();
        return list;
    }

    public ResponseDto getAllJobPage(Integer pageNumber, Integer pageSize) {
        if (pageSize >= 1 && pageNumber >= 1) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Jobs> jobsPage = jobRepo.findAll(pageable);
            long totalRecord = jobsPage.getTotalElements();
            List<Jobs> list = jobsPage.getContent();
            return new ResponseDto(totalRecord, list);
        } else {
            Pageable pageable = PageRequest.of(0, 20);
            Page<Jobs> jobsPage = jobRepo.findAll(pageable);
            long totalRecord = jobsPage.getTotalElements();
            List<Jobs> list = jobsPage.getContent();
            return new ResponseDto(totalRecord, list);
        }
    }

    public  List<Jobs> getSalaryJobs(){
        return  jobRepo.getSalaryJob();
    }
}
