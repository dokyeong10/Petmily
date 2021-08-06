package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.AnimalWait;

import java.util.List;

public interface AnimalService {
    AnimalWait patchAnimal(Long no, AnimalWait animalWait);
    AnimalWait createAnimal(AnimalRegisterPostReq registerInfo);
    void deleteAnimal(Long no);
    List<AnimalWait> searchAllAnimal(String key, String word, long no, boolean isLike);
}
