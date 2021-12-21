package com.itsol.recruit_managerment.request;

import com.itsol.recruit_managerment.model.AcademicLevel;
import com.itsol.recruit_managerment.model.Desiredwork;
import com.itsol.recruit_managerment.model.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationUserRequest {
    private Long id;
    private User users;
    private Desiredwork desiredwork;
    private AcademicLevel academicLevel;
    private String  skill;
    private Integer numberYearsExperience;
    private Integer desiredSalary;
    private String desiredWorkingAddress;
    private Boolean delete;


}