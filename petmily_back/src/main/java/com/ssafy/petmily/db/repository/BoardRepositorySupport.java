package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.BoardJoin;
import com.ssafy.petmily.db.entity.community.QBoard;
import com.ssafy.petmily.db.entity.community.QBoardJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoard qBoard =QBoard.board;
    QBoardJoin qBoardJoin = QBoardJoin.boardJoin;


    public List<BoardJoin> findBoardByNo(Long no) {
        List<BoardJoin> boardJoins = jpaQueryFactory.select(qBoardJoin).from(qBoardJoin)
                .where(qBoardJoin.no.eq(no)).fetch();
        return boardJoins;
    }

}
