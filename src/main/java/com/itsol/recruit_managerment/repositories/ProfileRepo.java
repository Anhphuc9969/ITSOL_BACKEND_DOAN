package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profiles,Long> {
}
