package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.Profiles;
import com.itsol.recruit_managerment.repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileImpl implements ProfileService{
    @Autowired
    private ProfileRepo profileRepository;

    @Override
    public List<Profiles> findAll() {
        try {
            return profileRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Profiles save(Profiles profileEntity) {
        return profileRepository.save(profileEntity);
    }

    @Override
    public Profiles findByID(Long id) {
        try {
            Optional<Profiles> optional = profileRepository.findById(id);
            if(optional.isPresent()) {
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
