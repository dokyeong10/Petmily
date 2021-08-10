package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.Reply;
import com.ssafy.petmily.db.repository.BoardRepository;
import com.ssafy.petmily.db.repository.ReplyRepository;
import com.ssafy.petmily.db.repository.ReplyRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ReplyRepositorySupport replyRepositorySupport;

    @Override
    public void createAgencyRepl(String agencycode, String contents, long no) {
        Reply reply = new Reply();
        reply.setBno(no);
        reply.setContents(contents);
        reply.setAgencycode(agencycode);
        reply.setGrpdeep((long) 0);
        reply.setGrporder((long) 0);
        reply.setRegdate(Calendar.getInstance().getTime());

        // 가장 큰 댓글 pk 값 가져오기 (부모 = 자신)
        long group = replyRepositorySupport.getReplMaxNo();
        System.out.println("========================= group 번호 : " + group);
        reply.setGrp(group + 1);
        System.out.println(reply.toString());
        replyRepository.save(reply);
        // 부모 = 자신으로 하기 위해 방금 자신이 넣은 no 조회
        long check = replyRepositorySupport.getReplMaxNo();
        if(check != group+1){
            reply.updateGroup(check);
            replyRepository.save(reply);
        }

        // 해당 게시글 댓글 수 증가
        Board board = boardRepository.findByNo(no).get();
        board.updateRepl();
        boardRepository.save(board);

        return;
    }

    @Override
    public void createUserRepl(long userno, String contents, long no) {
        Reply reply = new Reply();
        reply.setBno(no);
        reply.setContents(contents);
        reply.setUserno(userno);
        reply.setGrpdeep((long) 0);
        reply.setGrporder((long) 0);

        // 가장 큰 댓글 pk 값 가져오기 (부모 = 자신)
        long group = replyRepositorySupport.getReplMaxNo();
        reply.setGrp(group + 1);
        replyRepository.save(reply);
        // 부모 = 자신으로 하기 위해 방금 자신이 넣은 no 조회
        long check = replyRepositorySupport.getReplMaxNo();
        if(check != group){
            reply.updateGroup(check);
            replyRepository.save(reply);
        }

        // 해당 게시글 댓글 수 증가
        Board board = boardRepository.findByNo(no).get();
        board.updateRepl();
        boardRepository.save(board);

        return;
    }


}
