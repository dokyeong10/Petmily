package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.agency.Agency;

import com.ssafy.petmily.db.entity.agency.AgencyJoin;
import com.ssafy.petmily.db.entity.agency.QAgency;
import com.ssafy.petmily.db.entity.agency.QAgencyJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AgencyRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAgency qAgency = QAgency.agency;
    QAgencyJoin qAgencyJoin = QAgencyJoin.agencyJoin;

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

    public AgencyJoin findAgencyByCode(String agencycode){
        return jpaQueryFactory.select(qAgencyJoin).from(qAgencyJoin)
                .where(qAgencyJoin.agencycode.like(agencycode)).fetchOne();
    }
}
