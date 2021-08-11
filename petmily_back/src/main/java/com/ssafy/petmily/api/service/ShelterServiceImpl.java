package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;
import com.ssafy.petmily.db.entity.shelter.ShelterMain;
import com.ssafy.petmily.db.repository.ShelterRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService{

    @Autowired
    ShelterRepositorySupport shelterRepositorySupport;

    @Override
    public List<Shelter> searchShelterCode(String word) {
        return shelterRepositorySupport.searchShelterByName(word);
    }

    @Override
    public ShelterJoin searchShelterDetail(String code) {
        return shelterRepositorySupport.searchShelterDetail(code);
    }

    @Override
    public List<Shelter> getAllShelter() {
        return shelterRepositorySupport.getAllShelter();
    }

    @Override
    public List<ShelterMain> getAllShelterMain() {
        return shelterRepositorySupport.getAllShelterMain();
    }


}
