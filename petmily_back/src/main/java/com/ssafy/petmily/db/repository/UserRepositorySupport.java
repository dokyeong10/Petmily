package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.user.QUser;
import com.ssafy.petmily.db.entity.user.QUserJoin;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.entity.user.UserJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QUserJoin qUserJoin = QUserJoin.userJoin;

    public Optional<User> findUserByEmail(String email) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.email.eq(email)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
   }
    public Optional<User> findUserByEmailAndType(String email, String type) {
        System.out.println(email);
        System.out.println(type);
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.email.eq(email).and(qUser.type.eq(type))).fetchOne();
        System.out.println("--------------------------------" + user);
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    public User checkUserByEmailAndType(String email, String type) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.email.eq(email).and(qUser.type.eq(type))).fetchOne();
        return user;
    }

    public void deleteByEmailAndType(String email, String type){
        jpaQueryFactory.delete(qUser).where(qUser.email.eq(email).and(qUser.type.eq(type))).execute();
    }

    public UserJoin searchMypage(long no){
        return jpaQueryFactory.select(qUserJoin).from(qUserJoin).where(qUserJoin.no.eq(no)).fetchOne();
    }
}
