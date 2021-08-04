package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.db.entity.Agency;
import com.ssafy.petmily.db.entity.AnimalAdopt;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.repository.AnimalAdoptRepository;
import com.ssafy.petmily.db.repository.AnimalWaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalWaitRepository animalWaitRepository;

    @Autowired
    AnimalAdoptRepository animalAdoptRepository;


    @Override
    public AnimalWait createAnimal(AnimalRegisterPostReq registerInfo) {
        AnimalWait animalWait = new AnimalWait();
        Agency agency = new Agency();
        animalWait.setType(registerInfo.getType());
        animalWait.setSpecies(registerInfo.getSpecies());
        animalWait.setAddr(registerInfo.getAddr());
        animalWait.setAge(registerInfo.getAge());
        animalWait.setSex(registerInfo.isSex());
        animalWait.setNeutered(registerInfo.isNeutered());
        animalWait.setFind_date(registerInfo.getFind_date());
        agency.setAgencycode(registerInfo.getAgencycode());
        agency.setNo(registerInfo.getNo());
        return animalWaitRepository.save(animalWait);
    }
}
