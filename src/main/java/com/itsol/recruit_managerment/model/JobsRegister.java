package com.itsol.recruit_managerment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "jobs_register")
public class JobsRegister implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBS_REGISTER_SEQ")
    @SequenceGenerator(name = "JOBS_REGISTER_SEQ", sequenceName = "JOBS_REGISTER_SEQ", allocationSize = 1, initialValue = 1)
    int id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    int userId;

    @ManyToOne(targetEntity = ProfileStatus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_status_id", nullable = false)
    ProfileStatus profileStatus;

    @Column(name = "job_id", nullable = false)
    int jobId;

    @Column(name = "vacancies", nullable = false)
    int vacancies;

    @Column(name = "application_time", nullable = false)
    Date appliacationTime;

    @Column(name = "status", nullable = false)
    String status;

    @Column(name = "cv_file", nullable = false)
    String cvFile;

    @Column(name = "is_delete", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
}
