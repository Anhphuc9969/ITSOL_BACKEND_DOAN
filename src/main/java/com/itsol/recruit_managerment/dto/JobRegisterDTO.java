package com.itsol.recruit_managerment.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRegisterDTO {
    String id;
    String applicantName;
    String positionName;
    String jobRegisterStatus;
    String applicationTime;
}
