package com.itsol.recruit_managerment.service;

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
//        if (pageSize >= 1 && pageNumber >= 0) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Jobs> jobsPage = repoJpa.findAll(pageable);
        long totalRecord = jobsPage.getTotalElements();
        List<Jobs> list = jobsPage.getContent();
        return new ResponseDTO(totalRecord, list);
//        } else {
//            Pageable pageable = PageRequest.of(0, 20);
//            Page<Jobs> jobsPage = jobRepo.findAll(pageable);
//            long totalRecord = jobsPage.getTotalElements();
//            List<Jobs> list = jobsPage.getContent();
//            return new ResponseDto(totalRecord, list);
//        }
    }

    @Override
    public List<Jobs> search(JobSearchVM jobSearchVM) {
        List<Jobs> jobs = jobRepo.search(jobSearchVM);
        return jobs;
    }


}
