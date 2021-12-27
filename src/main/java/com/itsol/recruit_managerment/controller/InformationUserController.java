package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.GennericResponse.GenericResponse;
import com.itsol.recruit_managerment.dto.InformationUserDTO;
import com.itsol.recruit_managerment.model.AcademicLevel;
import com.itsol.recruit_managerment.model.DesiredWork;
import com.itsol.recruit_managerment.model.Profiles;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/pesonalInfomation")
public class InformationUserController {
    private static Logger logger = LogManager.getLogger(InformationUserController.class);
    @Autowired
    private DesireWorkService desiredWorkService;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AcademicLevelService academic_levelService;
    @GetMapping("/getInformationUserById/{id}")
    public ResponseEntity<?> getInformationUserById(@PathVariable("id") Long id){
        try {
            Profiles profileEntity = profileService.findByUserID(id);

            User userEntity = userInformationService.findByIDInformation(profileEntity.getUsers().getId());
            AcademicLevel academic_levelEntity = academic_levelService.findById(profileEntity.getAcademicLevel().getId());
            DesiredWork desiredWorkEntity = desiredWorkService.findById(profileEntity.getDesiredwork().getId());

            InformationUserDTO result = new InformationUserDTO();
            if(userEntity.getFullName()!=null)
                result.setFullName(userEntity.getFullName());
            if(userEntity.getEmail()!=null)
                result.setEmail(userEntity.getEmail());
            if(userEntity.getPhoneNumber()!=null && !userEntity.getPhoneNumber().equals(""))
                result.setPhone_number(userEntity.getPhoneNumber());
            if(userEntity.getHomeTown()!=null && !userEntity.getHomeTown().equals(""))
                result.setHomeTown(userEntity.getHomeTown());
            if(userEntity.getGender()!=null && !userEntity.getGender().equals(""))
                result.setGender(userEntity.getGender());
            if(userEntity.getBirthDay()!=null)
                result.setBirth_day(userEntity.getBirthDay());
            if(profileEntity.getSkill()!=null && !profileEntity.getSkill().equals(""))
                result.setSkill(profileEntity.getSkill());
            if(profileEntity.getNumberYearsExperience()!=null)
                result.setNumber_years_experience(profileEntity.getNumberYearsExperience());
            if(profileEntity.getDesiredWorkingAddress()!=null && !profileEntity.getDesiredWorkingAddress().equals(""))
                result.setDesired_working_address(profileEntity.getDesiredWorkingAddress());
            if(profileEntity.getDesiredSalary()!=null)
                result.setDesired_salary(profileEntity.getDesiredSalary());
            if(academic_levelEntity!=null){
                if(academic_levelEntity.getAcademicName()!=null && !academic_levelEntity.getAcademicName().equals(""))
                    result.setAcademic_name(academic_levelEntity.getAcademicName());
            }
            if(desiredWorkEntity.getDesiredworkname()!=null && !desiredWorkEntity.getDesiredworkname().equals(""))
                result.setDesiredworkname(desiredWorkEntity.getDesiredworkname());

            GenericResponse<Object> response = new GenericResponse<Object>(new Date(), HttpStatus.OK,
                    "Thành công", result);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra, vui lòng kiểm tra lại");
        }
    }
    @PutMapping("/updateInformationUserById/{id}")
    public ResponseEntity<?> updateInformationUserById(@PathVariable("id") Long id,@Valid @RequestBody InformationUserDTO form){
        try {

            Profiles profileEntity = profileService.findByUserID(id);

            User userEntity = userInformationService.findByIDInformation(profileEntity.getUsers().getId());

            userEntity.setEmail(form.getEmail().trim());
            userEntity.setPhoneNumber(form.getPhone_number().trim());
            userEntity.setHomeTown(form.getHomeTown());
            userEntity.setGender(form.getGender());
            userEntity.setBirthDay(form.getBirth_day());

            profileEntity.setSkill(form.getSkill().trim());
            profileEntity.setNumberYearsExperience(form.getNumber_years_experience());
            profileEntity.setDesiredSalary(form.getDesired_salary());
            profileEntity.setDesiredWorkingAddress(form.getDesired_working_address().trim());

            AcademicLevel academicLevel = academic_levelService.findAcademic_nameById(form.getAcademic_name().trim());
            if(academicLevel!=null){
                profileEntity.setAcademicLevel(academicLevel);
            }

            DesiredWork desiredwork = desiredWorkService.findDesiredWorkIdByDesiredworkname(form.getDesiredworkname().trim());
            if(desiredwork!=null){
                profileEntity.setDesiredwork(desiredwork);
            }

            InformationUserDTO result = new InformationUserDTO();

            result.setEmail(form.getEmail());
            result.setPhone_number(form.getPhone_number());
            result.setHomeTown(form.getHomeTown());
            result.setGender(form.getGender());
            result.setBirth_day(form.getBirth_day());
            result.setSkill(form.getSkill());
            result.setNumber_years_experience(form.getNumber_years_experience());
            result.setDesired_salary(form.getDesired_salary());
            result.setDesired_working_address(form.getDesired_working_address());
            result.setAcademic_name(form.getAcademic_name());
            result.setDesiredworkname(form.getDesiredworkname());

            GenericResponse<Object> response = new GenericResponse<Object>(new Date(), HttpStatus.OK,
                    "Thành công", result);

            profileEntity = profileService.save(profileEntity);
            userEntity = userInformationService.saveInformation(userEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra, vui lòng kiểm tra lại");
        }
    }
}
