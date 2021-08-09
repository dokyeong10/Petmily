package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.animal.AnimalLike;

import java.util.List;

public interface AnimalService {
    Animal patchAnimal(Long no, Animal animal);
    Animal createAnimal(AnimalRegisterPostReq registerInfo);
    void deleteAnimal(Long no);
    List<Animal> searchAllAnimal(String key, String word, long no, boolean isLike);
    AnimalJoin animaldetail(Long no);
}
