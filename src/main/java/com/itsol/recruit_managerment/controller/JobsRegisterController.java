package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.dto.ResponseDto;
import com.itsol.recruit_managerment.model.JobsRegister;
import com.itsol.recruit_managerment.service.JobRegisterServiceImpl;
import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{$spring.data.rest.base-path}/jobsRegister")

public class JobsRegisterController {

    @Autowired
    private JobRegisterServiceImpl jobRegisterImpl;

    @GetMapping("/getAll")
    @CrossOrigin
    public ResponseEntity<ResponseDto<JobsRegister>> getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        ResponseDto<JobsRegister> response = jobRegisterImpl.getAllJobsRegister(pageNumber, pageSize);
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
    
//    @GetMapping("/getByName/{fullName}")
//    @CrossOrigin
//    public List<JobsRegister> getByName(@PathVariable("fullName") String fullName){
//        return jobRegisterImpl.searchName(fullName);
//    }

//    @GetMapping("/getByDate/{time}")
//    @CrossOrigin
//    public List<JobsRegister> getByTime(@PathVariable("time") String time){
//        return jobRegisterImpl.searchDate(time);
//    }

//    @GetMapping("/getByVacancies/{vacancies}")
//    @CrossOrigin
//    public List<JobsRegister> getByVacancies(@PathVariable("vacancies") String vacancies){
//        return jobRegisterImpl.searchVacancies(vacancies);
//    }


}

