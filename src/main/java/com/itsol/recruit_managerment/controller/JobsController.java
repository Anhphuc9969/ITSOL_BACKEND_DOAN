package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.dto.ResponseDTO;
import com.itsol.recruit_managerment.dto.ResponsesDto;
import com.itsol.recruit_managerment.model.Jobs;
import com.itsol.recruit_managerment.service.JobsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    JobsServiceimpl jobsServiceimpl;



    @GetMapping("/getJob/{id}")
    @CrossOrigin
    public Jobs getJobs(@PathVariable("id") Long id) {
        return jobsServiceimpl.getFindByIdJob(id);
    }


    @GetMapping("/getAll")
    @CrossOrigin
    public ResponseEntity<List<Jobs>> getJobs() {
        return new ResponseEntity<List<Jobs>>(jobsServiceimpl.getAllJob(), HttpStatus.OK);
    }

    @GetMapping("/getAllPage")
    @CrossOrigin
    public ResponseEntity<ResponsesDto> getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        ResponsesDto responseDTO = jobsServiceimpl.getAllJobPage(pageNumber, pageSize);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/getSalary")
    @CrossOrigin
    public ResponseEntity<List<Jobs>> getSalary(){
      return new ResponseEntity<List<Jobs>>(jobsServiceimpl.getSalaryJobs(),HttpStatus.OK);
    }

}
