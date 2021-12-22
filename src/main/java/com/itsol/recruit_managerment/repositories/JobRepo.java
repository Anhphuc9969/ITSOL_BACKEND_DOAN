package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Jobs;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {

    @Query(value = "select * from jobs WHERE BETWEEN MAX_SALARY>=18000", nativeQuery = true)
    List<Jobs> getSalaryJob();
    @Query(value = "select  j.job_name, j.job_position, j.create_date,j.due_date,min_salary,j.max_salary ,j.views,is_delete from jobs j", nativeQuery = true)
    List<Jobs> getJobTable();
}
