package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.animal.QAnimalFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalFileRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QAnimalFile qAnimalFile = QAnimalFile.animalFile;

    public void deleteFile(Long no){
        jpaQueryFactory.delete(qAnimalFile).where(qAnimalFile.animalno.eq(no)).execute();
    }
}
