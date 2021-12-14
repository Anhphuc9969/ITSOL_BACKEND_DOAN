package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Jobs;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {
}
