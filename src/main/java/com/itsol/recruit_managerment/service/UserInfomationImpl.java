package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserInfomationImpl implements UserInformationService {
    @Autowired
    private UserRepo userRepository;
    @Override
    public List<User> findAllInformation() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User saveInformation(User userEntity) {

        return userRepository.save(userEntity);
    }

    @Override
    public User findByIDInformation(Long id) {

        try {
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
