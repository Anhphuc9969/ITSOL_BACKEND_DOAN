package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.JobRegisterDTO;
import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobRegisterStatus;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.repositories.JobRegisterStatusRepo;
import com.itsol.recruit_managerment.repositories.jobRegisterRp.JobRegisterRepo;
import com.itsol.recruit_managerment.repositories.jobRegisterRp.JobsRegisterRepositoryJpa;
import com.itsol.recruit_managerment.utils.DataUtil;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {

    @Autowired
    JobsRegisterRepositoryJpa jobsRegisterRepositoryJpa;

    @Autowired
    JobRegisterRepo jobRegisterRepo;

    @Autowired
    JobRegisterStatusRepo jobRegisterStatusRepo;

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

    public JobsRegister updateJobsRegister(JobRegisterDTO jobRegisterDTO) {
        try {
            JobsRegister jobsRegister = jobsRegisterRepositoryJpa.getById(jobRegisterDTO.getId());
            JobRegisterStatus jobRegisterStatus = jobRegisterStatusRepo.getById( jobRegisterDTO.getJobRegisterStatusId());
            jobsRegister.setReason(jobRegisterDTO.getReason());
            jobsRegister.setJobRegisterStatus(jobRegisterStatus);
            jobsRegisterRepositoryJpa.save(jobsRegister);
            return jobsRegister;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public byte[] downloadCv(int applicantId) throws IOException {
//        JobsRegister jobsRegister = jobsRegisterRepositoryJpa.findById(applicantId);
//        if (ObjectUtils.isEmpty(jobsRegister)) {
//            throw new NullPointerException("Could not found applicant");
//        }
//        return Files.readAllBytes(Paths.get(jobsRegister.getCvFile()));
//    }

    @Override
    public Resource downloadCv(int applicantId) throws IOException {
        JobsRegister jobsRegister = jobsRegisterRepositoryJpa.findById(applicantId);
        if (ObjectUtils.isEmpty(jobsRegister)) {
            throw new NullPointerException("Could not found applicant");
        }
        String cvFilePath = jobsRegister.getCvFile();
        Path file = Paths.get(cvFilePath);
        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Could not read the file!");
        }
        return resource;
    }

    @Override
    public String getCvFileName(String cvFilePath) {
        if (!DataUtil.isNotNullAndEmptyString(cvFilePath)) {
            throw new NullPointerException("CV file path is null");
        }
        String[] cvFilePaths = cvFilePath.split("/");
        return cvFilePaths[cvFilePaths.length - 1];
    }

}
