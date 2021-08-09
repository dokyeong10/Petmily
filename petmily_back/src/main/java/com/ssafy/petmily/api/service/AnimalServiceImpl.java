package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.animal.AnimalLike;
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
    public Animal patchAnimal(Long no, Animal animal) {
        final Optional<Animal> fetchedAnimal = animalWaitRepository.findByNo(no);
        if (fetchedAnimal.isPresent()) {
            if (animal.getType() != null) {
                fetchedAnimal.get().setType(animal.getType());
            }
            if (animal.getSpecies() != null) {
                fetchedAnimal.get().setSpecies(animal.getSpecies());
            }
            if (animal.getFind_addr() != null) {
                fetchedAnimal.get().setFind_addr(animal.getFind_addr());
            }
            if (animal.getAge() != -1) {
                fetchedAnimal.get().setAge(animal.getAge());
            }
            if (animal.isSex()) {
                fetchedAnimal.get().setSex(animal.isSex());
            }
            if (animal.isNeutered()) {
                fetchedAnimal.get().setNeutered(animal.isNeutered());
            }
            if (animal.getFind_date() != null) {
                fetchedAnimal.get().setFind_date(animal.getFind_date());
            }
            if (animal.getAgencycode() != null) {
                fetchedAnimal.get().setAgencycode(animal.getAgencycode());
            }
            return animalWaitRepository.save(fetchedAnimal.get());
        } else {
            return null;
        }
    }

    @Override
    public Animal createAnimal(AnimalRegisterPostReq registerInfo) {
        Animal animal = new Animal();
        animal.setType(registerInfo.getType());
        animal.setSpecies(registerInfo.getSpecies());
        animal.setFind_addr(registerInfo.getFind_addr());
        animal.setAge(registerInfo.getAge());
        animal.setSex(registerInfo.isSex());
        animal.setNeutered(registerInfo.isNeutered());
        animal.setFind_date(registerInfo.getFind_date());
        animal.setAgencycode(registerInfo.getAgencycode());
        return animalWaitRepository.save(animal);
    }

    @Override
    @Transactional
    public void deleteAnimal(Long no) {
        animalWaitRepository.deleteByNo(no);
    }

    @Override
    @Transactional
    public List<Animal> searchAllAnimal(String key, String word, long no, boolean isLike) {
        return animalWaitRepositorySupport.findAllAnimalWait(key, word, no, isLike);
    }

    @Override
    public AnimalJoin animaldetail(Long no) {
        AnimalJoin animal = animalWaitRepositorySupport.findAnimalByNo(no);
        return animal;
    }


}
