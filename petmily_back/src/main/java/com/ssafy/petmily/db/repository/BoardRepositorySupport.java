package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Repository
public class BoardRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoard qBoard =QBoard.board;
    QBoardJoin qBoardJoin = QBoardJoin.boardJoin;
    QReplyJoin qReplyJoin = QReplyJoin.replyJoin;
    QBoardFile qBoardFile = QBoardFile.boardFile;


    public BoardJoin findBoardByNo(Long no) {
        BoardJoin boardJoins = jpaQueryFactory.select(qBoardJoin).from(qBoardJoin)
                .where(qBoardJoin.no.eq(no)).fetchOne();
        return boardJoins;
    }

    public long getMaxNo() {
        long num =0;
        if(jpaQueryFactory.select(qBoard.no.max()).from(qBoard).fetchOne() ==null){
            num =0;
        }else{
            num =jpaQueryFactory.select(qBoard.no.max()).from(qBoard).fetchOne();
        }

        return num;
    }

    public List<BoardJoin> getBoardList(){
        return jpaQueryFactory.select(qBoardJoin).from(qBoardJoin).fetch();
    }

    @Transactional
    public void deleteFile(long no){
        jpaQueryFactory.delete(qBoardFile).where(qBoardFile.boardno.eq(no)).execute();
    }
}
