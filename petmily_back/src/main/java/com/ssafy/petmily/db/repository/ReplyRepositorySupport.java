package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.agency.QAgency;
import com.ssafy.petmily.db.entity.community.*;
import com.ssafy.petmily.db.entity.user.QUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QReply qReply = QReply.reply;
    QReplyJoin qReplyJoin = QReplyJoin.replyJoin;
    QBoard qBoard = QBoard.board;
    QAgency qAgency = QAgency.agency;
    QUser qUser = QUser.user;

    // 댓글 등록을 위한 댓글 최대 번호 구하기
    public long getReplMaxNo(){
        if(jpaQueryFactory.select(qReply.no.max()).from(qReply).fetchOne() == null){
            return 0;
        }
        return jpaQueryFactory.select(qReply.no.max()).from(qReply).fetchOne();
    }

    public List<ReplyJoin> getReplList(long bno){
        return jpaQueryFactory.select(qReplyJoin).from(qReplyJoin)
                .leftJoin(qAgency)
                .on(qAgency.agencycode.eq(qReplyJoin.agencycode))
                .leftJoin(qUser)
                .on(qUser.no.eq(qReplyJoin.userno))
                .where(qReplyJoin.bno.eq(bno))
                .orderBy(qReplyJoin.grp.asc(), qReplyJoin.grporder.asc()).fetch();
    }

    public long getlastReplno(long replno){
        return jpaQueryFactory.select(qReply.grporder.max()).from(qReply)
                .where(qReply.grp.eq(replno))
                .groupBy(qReply.grp).fetchOne();
    }


}
