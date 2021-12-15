package com.itsol.recruit_managerment.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "profiles")
public class Profiles implements Serializable {
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
    @SequenceGenerator(name = "PROFILES_SEQ", sequenceName = "PROFILES_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User users;

    @OneToOne(targetEntity = DesiredWork.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "desire_id", nullable = false)
    DesiredWork desiredwork;

    @OneToOne(targetEntity = AcademicLevel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_level_id", nullable = false)
    AcademicLevel academicLevel;

    @Column(name = "skill", nullable = false)
    String skill;

    @Column(name = "number_years_experience", nullable = false)
    Integer numberYearsExperience;

    @Column(name = "desired_salary", nullable = false)
    String desiredSalary;

    @Column(name = "desired_working_address", nullable = false)
        String desiredWorkingAddress;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_delete", nullable = false)
    boolean isDelete;

}
