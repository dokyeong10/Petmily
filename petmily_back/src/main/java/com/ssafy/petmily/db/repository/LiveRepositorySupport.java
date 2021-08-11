package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.Live.LiveRoom;
import com.ssafy.petmily.db.entity.Live.QLiveRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LiveRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QLiveRoom qLiveRoom = QLiveRoom.liveRoom;

    public List<LiveRoom> getLiveList(){
        return jpaQueryFactory.select(qLiveRoom).from(qLiveRoom).fetch();
    }

}
