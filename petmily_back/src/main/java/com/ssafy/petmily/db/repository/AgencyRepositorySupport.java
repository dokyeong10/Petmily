package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.Agency;
import com.ssafy.petmily.db.entity.QAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AgencyRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAgency qAgency = QAgency.agency;

    public Optional<Agency> findAgencyByUserid(String userid) {
        Agency agency = jpaQueryFactory.select(qAgency).from(qAgency)
                .where(qAgency.userid.eq(userid)).fetchOne();
        if(agency == null) return Optional.empty();
        return Optional.ofNullable(agency);
    }

}
