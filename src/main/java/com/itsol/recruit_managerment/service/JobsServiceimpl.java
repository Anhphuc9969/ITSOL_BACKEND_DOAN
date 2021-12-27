package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.JobDTO;
import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.Jobs;
import com.itsol.recruit_managerment.repositories.jobrepo.JobRepo;
import com.itsol.recruit_managerment.repositories.jobrepo.JobRepoJpa;
import com.itsol.recruit_managerment.vm.JobSearchVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsServiceimpl implements JobsService {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    JobRepoJpa repoJpa;

    public Jobs getFindByIdJob(Long id) {
        Optional<Jobs> jobRepoById = repoJpa.findById(id);
        return jobRepoById.orElse(null);
    }

    public List<Jobs> getAllJob() {
        List<Jobs> list = repoJpa.findAll();
        return list;
    }

    public ResponseDTO getAllJobPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Jobs> jobsPage = repoJpa.findAll(pageable);
        long totalRecord = jobsPage.getTotalElements();
        List<Jobs> list = jobsPage.getContent();
        return new ResponseDTO(totalRecord, list);
    }

    @Override
    public List<Jobs> search(JobSearchVM jobSearchVM) {
        List<Jobs> jobs = jobRepo.search(jobSearchVM);
        return jobs;
    }

}
