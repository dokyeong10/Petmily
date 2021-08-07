package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.QShelter;
import com.ssafy.petmily.db.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelterRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QShelter qShelter = QShelter.shelter;

    public List<Shelter> searchShelterByName(String word){
        return jpaQueryFactory.select(qShelter).from(qShelter)
                .where(qShelter.agencyname.like("%"+word+"%")).fetch();
    }
}
