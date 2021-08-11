package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.animal.QAnimalLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalLikeRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAnimalLike qAnimalLike = QAnimalLike.animalLike;

    public long getMaxNo() {
        long num =0;
        if(jpaQueryFactory.select(qAnimalLike.no.max()).from(qAnimalLike).fetchOne() ==null){
            num =0;
        }else{
            num =jpaQueryFactory.select(qAnimalLike.no.max()).from(qAnimalLike).fetchOne();
        }

        return num;
    }



}
