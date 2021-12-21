package com.itsol.recruit_managerment.dto;

import com.itsol.recruit_managerment.model.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class JobsRegisterDTO implements Serializable {
    Integer id;
    User user;
    Jobs jobs;
    JobStatus jobStatus;
    Profiles profiles;
    Date applicationTime;
    String status;
    String cvFile;
    Boolean isDelete;
}
