package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.agency.Agency;

import com.ssafy.petmily.db.entity.agency.QAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AgencyRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAgency qAgency = QAgency.agency;

    public Optional<Agency> findAgencyByEmail(String email) {
        Agency agency = jpaQueryFactory.select(qAgency).from(qAgency)
                .where(qAgency.email.eq(email)).fetchOne();
        if(agency == null) return Optional.empty();
        return Optional.ofNullable(agency);
    }

    public Agency checkAgencyByEmail(String email) {
        Agency agency = jpaQueryFactory.select(qAgency).from(qAgency)
                .where(qAgency.email.eq(email)).fetchOne();
        return agency;
    }
}
