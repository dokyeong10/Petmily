package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.*;
import com.ssafy.petmily.db.entity.animal.QAnimal;
import com.ssafy.petmily.db.entity.shelter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelterRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QShelter qShelter = QShelter.shelter;
    QShelterJoin qShelterJoin = QShelterJoin.shelterJoin;
    QShelterMain qShelterMain = QShelterMain.shelterMain;
    QAnimal qAnimal = QAnimal.animal;

    public List<Shelter> searchShelterByName(String word){
        return jpaQueryFactory.select(qShelter).from(qShelter)
                .where(qShelter.agencyname.like("%"+word+"%")).fetch();
    }

    public ShelterJoin searchShelterDetail(String code){
        return jpaQueryFactory.select(qShelterJoin).from(qShelterJoin)
                .where(qShelterJoin.agencycode.eq(code)).fetchOne();
    }

    public List<Shelter> getAllShelter(){
        return jpaQueryFactory.select(qShelter).from(qShelter).fetch();
    }

    public List<ShelterMain> getAllShelterMain(){
        return jpaQueryFactory.select(qShelterMain).from(qShelterMain).fetch();
    }

}
