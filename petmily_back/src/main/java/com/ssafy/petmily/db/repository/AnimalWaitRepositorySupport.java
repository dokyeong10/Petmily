//package com.ssafy.petmily.db.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.ssafy.petmily.db.entity.AnimalWait;
//import com.ssafy.petmily.db.entity.QAnimalLike;
//import com.ssafy.petmily.db.entity.QAnimalWait;
//import com.ssafy.petmily.db.entity.QUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class AnimalWaitRepositorySupport {
//
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    QAnimalWait qAnimalWait = QAnimalWait.animalWait;
//    QAnimalLike qAnimalLike = QAnimalLike.animalLike;
//    QUser qUser = QUser.user;
//
//    List<AnimalWait> findAllAnimalWait(String key, String word, Long no, boolean isLike){
//        List<AnimalWait> list = null;
//        if(key == null || word == null || key.equals("all")){ // 전체를 요청할 때
//            if(no < 0 || !isLike){ // 즐겨찾기 동물이 아닐 때
//                list = jpaQueryFactory.select(qAnimalWait).from(qAnimalWait).fetch();
//            }else if(no>0 && isLike){ // 즐겨찾기 동물일 때
//                list = jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
//                        .where(qAnimalWait.no.in(
//                                jpaQueryFactory.select(qAnimalLike.animalno).from(qAnimalLike)
//                                .where(qUser.no.eq(no))
//                        )).fetch();
//            }else if (key.equals("")){
//                if(no < 0 || !isLike) { // 즐겨찾기 동물이 아닐 때
////                    list = jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
////                            .where(qAnimalWait.)
//                }else if(no>0 && isLike){
//
//                }
//            }
//        }
//    }
//}
