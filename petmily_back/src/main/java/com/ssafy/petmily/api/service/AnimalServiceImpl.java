package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.db.entity.AnimalLike;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.repository.AnimalLikeRepository;
import com.ssafy.petmily.db.repository.AnimalWaitRepository;
import com.ssafy.petmily.db.repository.AnimalWaitRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalWaitRepository animalWaitRepository;

    @Autowired
    AnimalWaitRepositorySupport animalWaitRepositorySupport;

    @Autowired
    AnimalLikeRepository animalLikeRepository;

    @Override
    public AnimalWait patchAnimal(Long no, AnimalWait animalWait) {
        final Optional<AnimalWait> fetchedAnimal = animalWaitRepository.findByNo(no);
        if (fetchedAnimal.isPresent()) {
            if (animalWait.getType() != null) {
                fetchedAnimal.get().setType(animalWait.getType());
            }
            if (animalWait.getSpecies() != null) {
                fetchedAnimal.get().setSpecies(animalWait.getSpecies());
            }
            if (animalWait.getFind_addr() != null) {
                fetchedAnimal.get().setFind_addr(animalWait.getFind_addr());
            }
            if (animalWait.getAge() != -1) {
                fetchedAnimal.get().setAge(animalWait.getAge());
            }
            if (animalWait.isSex()) {
                fetchedAnimal.get().setSex(animalWait.isSex());
            }
            if (animalWait.isNeutered()) {
                fetchedAnimal.get().setNeutered(animalWait.isNeutered());
            }
            if (animalWait.getFind_date() != null) {
                fetchedAnimal.get().setFind_date(animalWait.getFind_date());
            }
            if (animalWait.getAgencycode() != null) {
                fetchedAnimal.get().setAgencycode(animalWait.getAgencycode());
            }
            return animalWaitRepository.save(fetchedAnimal.get());
        } else {
            return null;
        }
    }

    @Override
    public AnimalWait createAnimal(AnimalRegisterPostReq registerInfo) {
        AnimalWait animalWait = new AnimalWait();
        animalWait.setType(registerInfo.getType());
        animalWait.setSpecies(registerInfo.getSpecies());
        animalWait.setFind_addr(registerInfo.getFind_addr());
        animalWait.setAge(registerInfo.getAge());
        animalWait.setSex(registerInfo.isSex());
        animalWait.setNeutered(registerInfo.isNeutered());
        animalWait.setFind_date(registerInfo.getFind_date());
        animalWait.setAgencycode(registerInfo.getAgencycode());
        return animalWaitRepository.save(animalWait);
    }

    @Override
    @Transactional
    public void deleteAnimal(Long no) {
        animalWaitRepository.deleteByNo(no);
    }

    @Override
    @Transactional
    public List<AnimalWait> searchAllAnimal(String key, String word, long no, boolean isLike) {
        return animalWaitRepositorySupport.findAllAnimalWait(key, word, no, isLike);
    }

    @Override
    public AnimalLike addlike(LikeRegisterPostReq likeRegisterPostReq) {
        AnimalLike animalLike = new AnimalLike();
        animalLike.setNo(likeRegisterPostReq.getNo());
        ;
        animalLike.setAnimalno(likeRegisterPostReq.getAnimalno());
        animalLike.setUserno(likeRegisterPostReq.getUserno());
        return animalLikeRepository.save(animalLike);
    }


    @Override
    public List<AnimalLike> searchLikeAnimal(Long userno) {
        return animalWaitRepositorySupport.findAnimalByUserno(userno);
    }

    @Override
    @Transactional
    public boolean deleteLike(Long no) {
        Optional<AnimalLike> animal = animalLikeRepository.findByNo(no);
        if (animal.isPresent()) {
            animalLikeRepository.deleteByNo(no);
            return true;
        } else {
            return false;
        }
    }


}
