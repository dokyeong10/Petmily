package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;

import com.ssafy.petmily.db.entity.QUser;
import com.ssafy.petmily.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userid) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userid.eq(userid)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
   }
}