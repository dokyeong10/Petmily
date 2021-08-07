package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.db.entity.AnimalLike;
import com.ssafy.petmily.db.entity.AnimalWait;

import java.util.List;

public interface AnimalService {
    AnimalWait patchAnimal(Long no, AnimalWait animalWait);
    AnimalWait createAnimal(AnimalRegisterPostReq registerInfo);
    void deleteAnimal(Long no);
    List<AnimalWait> searchAllAnimal(String key, String word, long no, boolean isLike);
    AnimalLike addlike(LikeRegisterPostReq likeRegisterPostReq);
    List<AnimalLike> searchLikeAnimal(Long userno);
    void deleteLike(Long no);
}
