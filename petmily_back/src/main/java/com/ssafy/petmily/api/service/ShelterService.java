package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;

import java.util.List;

public interface ShelterService {

    List<Shelter> searchShelterCode(String word);
    ShelterJoin searchShelterDetail(String code);
    List<Shelter> getAllShelter();
}
