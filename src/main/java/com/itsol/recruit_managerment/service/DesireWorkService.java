package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.Desiredwork;

public interface DesireWorkService {
    Desiredwork findById(Long id);
    Desiredwork findDesiredWorkIdByDesiredworkname(String desiredworkname);
    public Desiredwork save(Desiredwork desiredwork);
}
