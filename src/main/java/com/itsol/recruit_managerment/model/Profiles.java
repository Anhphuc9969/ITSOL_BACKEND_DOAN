package com.itsol.recruit_managerment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profiles")
public class Profiles implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
    @SequenceGenerator(name = "PROFILES_SEQ", sequenceName = "PROFILES_SEQ", allocationSize = 1, initialValue = 1)
    int id;

    @OneToOne(targetEntity = Desiredwork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "desire_id", nullable = false)
    Desiredwork desiredwork;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User users;

    @OneToOne(targetEntity = AcademicLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_level_id", nullable = false)
    AcademicLevel academicLevel;

    @Column(name = "skill", nullable = false)
    String skill;

    @Column(name = "number_years_experience", nullable = false)
    int numberYearsExperience;

    @Column(name = "desired_salary", nullable = false)
    int desiredSalary;

    @Column(name = "desired_working_address", nullable = false)
    String desiredWorkingAddress;

    @Column(name = "is_delete", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
}
