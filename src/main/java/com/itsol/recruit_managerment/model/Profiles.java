package com.itsol.recruit_managerment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profiles")
public class Profiles implements Serializable {
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
    @SequenceGenerator(name = "PROFILES_SEQ", sequenceName = "PROFILES_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User users;

    @ManyToOne(targetEntity = Desiredwork.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "desire_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Desiredwork desiredwork;

    @OneToOne(targetEntity = AcademicLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_level_id", nullable = false)
    AcademicLevel academicLevel;

    @Column(name = "skill", nullable = false)
    String skill;

    @Column(name = "number_years_experience", nullable = false)
    Integer numberYearsExperience;

    @Column(name = "desired_salary", nullable = false)
    Integer desiredSalary;

    @Column(name = "desired_working_address", nullable = false)
    String desiredWorkingAddress;

    @Column(name = "is_delete", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
}
