package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AnimalWaitRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAnimalWait qAnimalWait = QAnimalWait.animalWait;
    QAnimalLike qAnimalLike = QAnimalLike.animalLike;
    QUser qUser = QUser.user;
    QShelter qShelter = QShelter.shelter;

   public List<AnimalWait> findAllAnimalWait(String key, String word, Long no, boolean isLike) {
        System.out.println("===================== key : " + key);
        System.out.println("===================== word : " + word);
        System.out.println("===================== no : " + no);
        System.out.println("===================== Like? : " + isLike);

        if (key == null || word == null || word.equals(" ") || key.equals("all")) { // 전체를 요청할 때
            if (no <= 0 || !isLike) { // 즐겨찾기 동물이 아닐 때
                return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait).fetch();
            } else if (no > 0 && isLike) { // 즐겨찾기 동물일 때
                return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                        .where(qAnimalWait.no.in(
                                jpaQueryFactory.select(qAnimalLike.animalno).from(qAnimalLike)
                                        .where(qUser.no.eq(no))
                        )).fetch();
            }


        } else {
            if(key.equals("type")) { // 동물 종류로 검색
                if (no <= 0 || !isLike) { // 즐겨찾기 동물이 아닐 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.species.like("%"+word+"%")).fetch();
                } else if (no > 0 && isLike) { // 즐겨찾기 동물일 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.species.like("%"+word+"%")
                                    .and(qAnimalWait.no.in(jpaQueryFactory.select(qAnimalLike.animalno).from(qAnimalLike).where(qUser.no.eq(no))))
                            ).fetch();
                }


            } else if (key.equals("addr")) { // 동물 보호소 위치로 검색
                if (no <= 0 || !isLike) { // 즐겨찾기 동물이 아닐 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.agencycode
                                    .in(jpaQueryFactory.select(qShelter.agencycode).from(qShelter).where(qShelter.addr.like("%"+word+"%")))
                            ).fetch();
                } else if (no > 0 && isLike) { // 즐겨찾기 동물일 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.agencycode
                                    .in(jpaQueryFactory.select(qShelter.agencycode).from(qShelter).where(qShelter.addr.like("%"+word+"%")))
                                    .and(qAnimalWait.no.in(jpaQueryFactory.select(qAnimalLike.animalno).from(qAnimalLike).where(qUser.no.eq(no))))
                            ).fetch();
                }


            } else if (key.equals("agencyname")) { // 동물 보호소 이름으로 검색
                if (no <= 0 || !isLike) { // 즐겨찾기 동물이 아닐 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.agencycode
                                    .in(jpaQueryFactory.select(qShelter.agencycode).from(qShelter).where(qShelter.agencyname.like("%"+word+"%")))
                            ).fetch();
                } else if (no > 0 && isLike) { // 즐겨찾기 동물일 때
                    return jpaQueryFactory.select(qAnimalWait).from(qAnimalWait)
                            .where(qAnimalWait.agencycode
                                    .in(jpaQueryFactory.select(qShelter.agencycode).from(qShelter).where(qShelter.agencyname.like("%"+word+"%")))
                                    .and(qAnimalWait.no.in(jpaQueryFactory.select(qAnimalLike.animalno).from(qAnimalLike).where(qAnimalLike.userno.eq(no))))
                            ).fetch();
                }

            }
        }
        return null;
    }
}
