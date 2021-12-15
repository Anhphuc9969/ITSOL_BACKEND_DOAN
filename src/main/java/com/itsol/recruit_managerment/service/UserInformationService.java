package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.User;

import java.util.List;

public interface UserInformationService {

    List<User> findAllInformation();

    User saveInformation(User userEntity);

    User findByIDInformation(Long id);
}
