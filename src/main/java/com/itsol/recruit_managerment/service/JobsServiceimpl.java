package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.Jobs;
import com.itsol.recruit_managerment.repositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsServiceimpl {

    @Autowired
    JobRepo jobRepo;

    public Jobs getFindByIdJob(Long id){
        Optional<Jobs> jobRepoById = jobRepo.findById(id);
        return jobRepoById.orElse(null);
    }

    public List<Jobs> getAllJob(){
        List<Jobs> list = jobRepo.findAll();
        return list;
    }

    public ResponseDTO getAllJobPage(Integer pageNumber, Integer pageSize){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.ASC,"applicationTime"));
        Page<Jobs> jobsPage=jobRepo.findAll(pageable);
        long totalRecord=jobsPage.getTotalElements();
        List<Jobs> list = jobsPage.getContent();
        return new ResponseDTO(totalRecord,list);
    }





}
