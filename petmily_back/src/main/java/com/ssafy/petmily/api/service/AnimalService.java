package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;

import java.util.List;

public interface AnimalService {
    Animal patchAnimal(Long no, AnimalRegisterPostReq animal);
    Animal createAnimal(AnimalRegisterPostReq registerInfo);
    void deleteAnimal(Long no);
    List<Animal> searchAllAnimal(String key, String word, long no, boolean isLike);
    AnimalJoin animaldetail(Long no);
    List<Animal> getThreeAnimal();
    AnimalFile fileUpload(String filedir, String extension);
    AnimalFile fileUpdate(long no, String filedir, String extension);
    void deleteFile(Long no);
}
