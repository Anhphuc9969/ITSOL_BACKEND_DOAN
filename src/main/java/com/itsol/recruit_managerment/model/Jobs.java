package com.itsol.recruit_managerment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ROLE_ID")
    @SequenceGenerator(name = "GEN_ROLE_ID", sequenceName = "ROLE_SEQ", allocationSize = 1)
//    @Column(name = "ID", nullable = false)
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "fullName không được để trống")
    @ManyToOne(targetEntity = JobStatus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_status_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    JobStatus jobStatus;

    @OneToOne(targetEntity = MethodWork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "method_work_id", nullable = false)
    @JsonIgnore
    MethodWork methodWork;


    @OneToOne(targetEntity = AcademicLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_level_id", nullable = false)
    @JsonIgnore
    AcademicLevel academicLevel;


    @OneToOne(targetEntity = LevelRank.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "level_rank_id", nullable = false)
    @JsonIgnore
    LevelRank levelRank;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "create_id", nullable = false)
    User createId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", nullable = false)
    User contactId;

    @Column(name = "job_name", nullable = false)
    String jobName;

    @Column(name = "job_position", nullable = false)
    String jobPosition;

    @Column(name = "number_experience", nullable = false)
    String numberExperience;

    @Column(name = "address_work", nullable = false)
    String addressWork;

    @Column(name = "quantity_person", nullable = false)
    int quantityPerson;

    @Column(name = "create_date", nullable = false)
    Date createDate;

    @Column(name = "due_date", nullable = false)
    Date dueDate;

    @Column(name = "skills", nullable = false)
    String skills;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "interrest", nullable = false)
    String interest;

    @Column(name = "min_salary", nullable = false)
    float minSalary;

    @Column(name = "max_salary", nullable = false)
    float maxSalary;

    @Column(name = "views", nullable = false)
    int view;

    @Column(name = "is_delete", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;


}
