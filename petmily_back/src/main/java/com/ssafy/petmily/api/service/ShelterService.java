package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.Shelter;

import java.util.List;

public interface ShelterService {

    List<Shelter> searchShelterCode(String word);
    Shelter searchShelterDetail(long no);
}
