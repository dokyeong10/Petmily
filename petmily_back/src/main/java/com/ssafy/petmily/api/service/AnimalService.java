package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.AnimalWait;

public interface AnimalService {
    AnimalWait patchAnimal(Long no, AnimalWait animalWait);
    AnimalWait createAnimal(AnimalRegisterPostReq registerInfo);
    void deleteAnimal(Long no);
}
