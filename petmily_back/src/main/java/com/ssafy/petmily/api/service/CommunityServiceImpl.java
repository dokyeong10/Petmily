package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.Reply;
import com.ssafy.petmily.db.entity.community.ReplyJoin;
import com.ssafy.petmily.db.repository.BoardRepository;
import com.ssafy.petmily.db.repository.ReplyRepository;
import com.ssafy.petmily.db.repository.ReplyRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ReplyRepositorySupport replyRepositorySupport;

    @Override
    public void createAgencyRepl(String agencycode, String contents, long bno) {
        Reply reply = new Reply();
        reply.setBno(bno);
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
        Board board = boardRepository.findByNo(bno).get();
        board.updateRepl(1);
        boardRepository.save(board);

        return;
    }

    @Override
    public void createUserRepl(long userno, String contents, long bno) {
        Reply reply = new Reply();
        reply.setBno(bno);
        reply.setContents(contents);
        reply.setUserno(userno);
        reply.setGrpdeep((long) 0);
        reply.setGrporder((long) 0);
        reply.setRegdate(Calendar.getInstance().getTime());

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
        Board board = boardRepository.findByNo(bno).get();
        board.updateRepl(1);
        boardRepository.save(board);

        return;
    }

    @Override
    public void createAgencyReplRe(String agencycode, String contents, long no, long replno) {
        Reply reply = new Reply();
        reply.setBno(no);
        reply.setContents(contents);
        reply.setAgencycode(agencycode);
        reply.setGrpdeep((long) 1);

        // 댓글 순서
        long lastno = replyRepositorySupport.getlastReplno(replno);
        System.out.println("=========================== lastno : " +lastno);
        reply.setGrporder(lastno + 1);

        reply.setRegdate(Calendar.getInstance().getTime());
        reply.setGrp(replno);
        replyRepository.save(reply);

        // 해당 게시글 댓글 수 + 1 증가
        Board board = boardRepository.findByNo(no).get();
        board.updateRepl(1);
        boardRepository.save(board);

        return;
    }

    @Override
    public void createUserReplRe(long userno, String contents, long bno, long replno) {
        Reply reply = new Reply();
        reply.setBno(bno);
        reply.setContents(contents);
        reply.setUserno(userno);
        reply.setGrpdeep((long) 1);

        // 댓글 순서
        long lastno = replyRepositorySupport.getlastReplno(replno);
        System.out.println("=========================== lastno : " +lastno);
        reply.setGrporder(lastno + 1);

        reply.setRegdate(Calendar.getInstance().getTime());
        reply.setGrp(replno);
        replyRepository.save(reply);

        // 해당 게시글 댓글 수 증가
        Board board = boardRepository.findByNo(bno).get();
        board.updateRepl(1);
        boardRepository.save(board);

        return;
    }

    @Override
    public void deleteRepl(long replno, long bno) {
        // 댓글 조회 (모댓글인지 답글인지 판단)
        Reply reply = replyRepository.findByNo(replno);
        long grpdeep = reply.getGrpdeep();
        long grp = reply.getGrp();

        if(grpdeep == 0){ // 모댓글일 때

        }else if(grpdeep == 1){ // 자식 답글일 때
            replyRepository.deleteByNo(replno);

            // 모댓글 조회
            Reply parent = replyRepositorySupport.getReplParent(grp);
            String contents = parent.getContents();
            long parentNo = parent.getNo();

            // 모댓글이 삭제된 경우
            if(contents.equals("")){
                // 답글의 갯수 조회
                long cnt = replyRepositorySupport.getReReplcnt(parentNo);

                if(cnt == 0) { // 답글이 더이상 없으면
                    replyRepository.deleteByNo(parentNo); // 모댓글 테이블에서 제거
                }
            }

                // 게시글 댓글 수 감소
                Board board = boardRepository.findByNo(bno).get();
                board.updateRepl(-1);
                boardRepository.save(board);

        }
    }


}
