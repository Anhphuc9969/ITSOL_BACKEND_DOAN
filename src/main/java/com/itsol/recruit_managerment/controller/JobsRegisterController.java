package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.dto.JobRegisterDTO;
import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.service.JobRegisterServiceImpl;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public JobsRegister updateJobRegister(@Valid @RequestBody JobRegisterDTO jobRegisterDTO){
        return jobRegisterImpl.updateJobsRegister(jobRegisterDTO) ;
    }

//    @GetMapping("/cv/download/{applicantId}")
//    @CrossOrigin
//    public ResponseEntity<byte[]> downloadApplicantCv(@PathVariable("applicantId") int applicantId) throws Exception {
//        byte[] cvContent = jobRegisterImpl.downloadCv(applicantId);
//        JobsRegister jobsRegister = jobRegisterImpl.getJobsRegister(applicantId);
//        String mimetype = jobsRegister.getCvMimetype();
//        String cvFileName = jobRegisterImpl.getCvFileName(jobsRegister.getCvFile());
//        if (ObjectUtils.isEmpty(jobsRegister)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(cvContent, HttpUtil.createHeaderForDownloadFile(cvFileName, mimetype), HttpStatus.OK);
//    }

    @GetMapping("/cv/download/{applicantId}")
    @CrossOrigin
    public ResponseEntity<Resource> downloadApplicantCv(@PathVariable("applicantId") int applicantId) throws Exception {
        Resource resource = jobRegisterImpl.downloadCv(applicantId);
        Path path = resource.getFile()
                .toPath();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}

