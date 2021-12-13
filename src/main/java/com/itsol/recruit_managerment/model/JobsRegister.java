package com.itsol.recruit_managerment.model;

import javax.persistence.*;
import java.util.Date;

public class JobsRegister {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBS_REGISTER_SEQ")
    @SequenceGenerator(name = "JOBS_REGISTER_SEQ", sequenceName = "JOBS_REGISTER_SEQ", allocationSize = 1, initialValue = 1)
    int id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
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
    boolean isDelete;
}
