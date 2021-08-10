package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.BoardJoin;
import com.ssafy.petmily.db.entity.community.QBoard;
import com.ssafy.petmily.db.entity.community.QBoardJoin;
import com.ssafy.petmily.db.entity.community.QReplyJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoard qBoard =QBoard.board;
    QBoardJoin qBoardJoin = QBoardJoin.boardJoin;
    QReplyJoin qReplyJoin = QReplyJoin.replyJoin;


    public BoardJoin findBoardByNo(Long no) {
        BoardJoin boardJoins = jpaQueryFactory.select(qBoardJoin).from(qBoardJoin)
                .where(qBoardJoin.no.eq(no)).fetchOne();
        return boardJoins;
    }

}
