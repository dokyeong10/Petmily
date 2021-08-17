package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;
import com.ssafy.petmily.db.entity.community.BoardJoin;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@Service("communityService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Autowired
    ReplyRepositorySupport replyRepositorySupport;



    @Override
    public Board createBoard(ComuRegisterPostReq comuRegisterPostReq) {
        Board board = new Board();
        board.setUserno(comuRegisterPostReq.getUserno());
        board.setAgencycode(comuRegisterPostReq.getAgencycode());
        board.setTitle(comuRegisterPostReq.getTitle());
        board.setContents(comuRegisterPostReq.getContents());
        board.setReg_date(Calendar.getInstance().getTime());
        System.out.println("============================ user board : " + board.toString());

        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long no) {
        boardRepository.deleteByNo(no);
    }

    @Override
    public Board updateBoard(ComuRegisterPostReq comuRegisterPostReq) {
        Board board = boardRepository.findByNo(comuRegisterPostReq.getNo()).get();
        if(comuRegisterPostReq.getContents() != null)
            board.setContents(comuRegisterPostReq.getContents());
        if(comuRegisterPostReq.getTitle() != null)
            board.setTitle(comuRegisterPostReq.getTitle());
       return boardRepository.save(board);
    }


    @Override
    public BoardFile fileUpload(String filedir, String extension) {
        BoardFile boardFile = new BoardFile();
        long max =  boardRepositorySupport.getMaxNo();
        System.out.println(max+" ===============");
        boardFile.setBoardno(max);
        boardFile.setFile(filedir);
        boardFile.setExtension(extension);
        return boardFileRepository.save(boardFile);
    }

    @Override
    public BoardFile fileUpdate(long no, String filedir, String extension) {
        BoardFile boardFile = new BoardFile();
        boardFile.setBoardno(no);
        boardFile.setFile(filedir);
        boardFile.setExtension(extension);
        return boardFileRepository.save(boardFile);
    }

    @Override
    public BoardJoin boarddetail(Long no) {
        BoardJoin boardJoins = boardRepositorySupport.findBoardByNo(no);
        boardJoins.setReplyJoins(replyRepositorySupport.getReplList(no));
        return boardJoins;
    }

    @Override
    public List<BoardJoin> getBoadList(long userno, String word) {
        return boardRepositorySupport.getBoardList(userno, word);
    }

    @Override
    public void deleteFile(long no) {
        boardRepositorySupport.deleteFile(no);
    }


}
