package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.entity.QAnimalWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SearchAnimalRepositorySupport {

//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    QAnimalWait qAnimalWait = QAnimalWait.animalWait;

//    public Optional<AnimalWait> Search(Long no, String type){
//        AnimalWait animalWait = jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
//                .where(qAnimalWait.type.eq(type)).fetchOne();
//        return Optional.ofNullable(animalWait);
//    }


}
