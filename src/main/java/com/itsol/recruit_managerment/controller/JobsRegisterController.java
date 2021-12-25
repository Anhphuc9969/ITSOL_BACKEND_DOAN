package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.dto.JobRegisterDTO;
import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.service.JobRegisterServiceImpl;
import com.itsol.recruit_managerment.utils.HttpUtil;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/{$spring.data.rest.base-path}/jobsRegister")

public class JobsRegisterController {

    @Autowired
    private JobRegisterServiceImpl jobRegisterImpl;

    @GetMapping("/getAll")
    @CrossOrigin
    public ResponseEntity<ResponseDTO<JobsRegister>> getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        ResponseDTO<JobsRegister> response = jobRegisterImpl.getAllJobsRegister(pageNumber, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @CrossOrigin
    public JobsRegister getById(@PathVariable("id") int id) {
        return jobRegisterImpl.getJobsRegister(id);
    }


    @PostMapping("/search")
    @CrossOrigin
    public List<JobsRegister> search(@RequestBody JobRegisterSearchVm jobRegisterSearchVm) {
        return jobRegisterImpl.search(jobRegisterSearchVm);
    }

    @PutMapping("/update")
    @CrossOrigin
    public ResponseEntity<String> updateJobRegister(@Valid @RequestBody JobRegisterDTO jobRegisterDTO){
        Boolean flag = jobRegisterImpl.updateJobsRegister(jobRegisterDTO) ;
        if (flag){
            return ResponseEntity.ok().body("Update thành công");
        }
        return ResponseEntity.ok().body("Update thất bại");
    }

    @GetMapping("/cv/download/{applicantId}")
    @CrossOrigin
    public ResponseEntity<byte[]> downloadApplicantCv(@PathVariable("applicantId") int applicantId) throws Exception {
        byte[] cvContent = jobRegisterImpl.downloadCv(applicantId);
        JobsRegister jobsRegister = jobRegisterImpl.getJobsRegister(applicantId);
        String mimetype = jobsRegister.getCvMimetype();
        String cvFileName = jobRegisterImpl.getCvFileName(jobsRegister.getCvFile());
        if (ObjectUtils.isEmpty(jobsRegister)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cvContent, HttpUtil.createHeaderForDownloadFile(cvFileName, mimetype), HttpStatus.OK);
    }

}

