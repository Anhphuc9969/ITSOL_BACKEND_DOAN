package com.itsol.recruit_managerment.repositories;


import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTP, Long> {

    @Query("FROM OTP o WHERE o.user = :user")
    Optional<OTP> findByUser(@Param("user") User user);


}
