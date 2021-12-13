package com.itsol.recruit_managerment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "method_word")
public class MethodWork {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METHOD_WORK_SEQ")
    @SequenceGenerator(name = "METHOD_WORK_SEQ", sequenceName = "METHOD_WORK_SEQ", allocationSize = 1, initialValue = 1)
    int id;

//    @OneToOne(targetEntity = Jobs.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "jobs_id", nullable = false)
//    Jobs jobs;

    @Column(name = "method_name", nullable = false)
    String methodName;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "is_delete", nullable = false)
    boolean isDelete;
}
