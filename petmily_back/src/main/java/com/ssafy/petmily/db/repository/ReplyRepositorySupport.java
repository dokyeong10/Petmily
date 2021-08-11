package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.community.QBoard;
import com.ssafy.petmily.db.entity.community.QReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReplyRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QReply qReply = QReply.reply;
    QBoard qBoard = QBoard.board;

    // 댓글 등록을 위한 댓글 최대 번호 구하기
    public long getReplMaxNo(){
        if(jpaQueryFactory.select(qReply.no.max()).from(qReply).fetchOne() == null){
            return 0;
        }
        return jpaQueryFactory.select(qReply.no.max()).from(qReply).fetchOne();

    }
}
