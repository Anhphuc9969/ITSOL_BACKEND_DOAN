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
@Table(name = "level_rank")
public class LevelRank {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEVEL_RANK_SEQ")
    @SequenceGenerator(name = "LEVEL_RANK_SEQ", sequenceName = "LEVEL_RANK_SEQ", allocationSize = 1, initialValue = 1)
    int id;

//    @OneToOne(targetEntity = Jobs.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "jobs_id", nullable = false)
//    Jobs jobs;

    @Column(name = "level_name", nullable = false)
    String levelName;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "is_delete", nullable = false)
    boolean isDelete;
}
