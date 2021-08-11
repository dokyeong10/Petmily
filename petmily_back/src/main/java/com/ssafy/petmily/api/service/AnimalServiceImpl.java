package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.repository.AnimalFileRepository;
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

    @Autowired
    AnimalFileRepository animalFileRepository;

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
            if (animal.getText() != null) {
                fetchedAnimal.get().setText(animal.getText());
            }
            if (animal.getProfile_img() != null) {
                fetchedAnimal.get().setProfile_img(animal.getProfile_img());
            }
            return animalWaitRepository.save(fetchedAnimal.get());
        } else {
            return null;
        }
    }


    // 동물 등록
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
        animal.setProfile_img(registerInfo.getProfile_img());
        animal.setText(registerInfo.getText());
        System.out.println("============================ animal register : " + animal.toString());
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
        AnimalJoin animalJoins = animalWaitRepositorySupport.findAnimalByNo(no);
        return animalJoins;
    }

    @Override
    public List<Animal> getThreeAnimal() {
        return animalWaitRepositorySupport.getThreeAnimal();
    }

    @Override
    public AnimalFile fileUpload(String filedir, String extension) {
        AnimalFile animalFile = new AnimalFile();
        Animal animal = new Animal();
        long max =  animalWaitRepositorySupport.getMaxNo();
        System.out.println(max+" ===============");
        animalFile.setAnimalno(max);
        //animalFile.setAnimalno(filePostReq.getAnimalno());
        animalFile.setFile(filedir);
        animalFile.setExtension(extension);
        return animalFileRepository.save(animalFile);
    }


}
