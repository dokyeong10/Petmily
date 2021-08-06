package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.repository.AnimalWaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalWaitRepository animalWaitRepository;

    @Override
    public AnimalWait patchAnimal(Long no, AnimalWait animalWait) {
        final Optional<AnimalWait> fetchedAnimal = animalWaitRepository.findByNo(no);
        if(fetchedAnimal.isPresent()){
            if(animalWait.getType() != null){
                fetchedAnimal.get().setType(animalWait.getType());
            }
            if(animalWait.getSpecies() != null){
                fetchedAnimal.get().setSpecies(animalWait.getSpecies());
            }
            if(animalWait.getAddr() != null){
                fetchedAnimal.get().setAddr(animalWait.getAddr());
            }
            if(animalWait.getAge() != -1){
                fetchedAnimal.get().setAge(animalWait.getAge());
            }
            if(animalWait.isSex()){
                fetchedAnimal.get().setSex(animalWait.isSex());
            }
            if(animalWait.isNeutered()){
                fetchedAnimal.get().setNeutered(animalWait.isNeutered());
            }
            if(animalWait.getFind_date()!=null){
                fetchedAnimal.get().setFind_date(animalWait.getFind_date());
            }
            if(animalWait.getAgencycode()!=null){
                fetchedAnimal.get().setAgencycode(animalWait.getAgencycode());
            }
            return animalWaitRepository.save(fetchedAnimal.get());
        }
        else{
            return null;
        }
    }

    @Override
    public AnimalWait createAnimal(AnimalRegisterPostReq registerInfo) {
        AnimalWait animalWait = new AnimalWait();
        animalWait.setType(registerInfo.getType());
        animalWait.setSpecies(registerInfo.getSpecies());
        animalWait.setAddr(registerInfo.getAddr());
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
    public List<AnimalWait> searchAllAnimal(String key, String word, long no, boolean isLike) {
        return null;
    }
}
