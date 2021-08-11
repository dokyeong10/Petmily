package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;
import com.ssafy.petmily.db.entity.community.BoardJoin;
import com.ssafy.petmily.db.repository.BoardFileRepository;
import com.ssafy.petmily.db.repository.BoardRepository;
import com.ssafy.petmily.db.repository.BoardRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("communityService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Override
    public Board createBoard(ComuRegisterPostReq comuRegisterPostReq) {
        Board board = new Board();
        board.setUserno(comuRegisterPostReq.getUserno());
        board.setTitle(comuRegisterPostReq.getTitle());
        board.setContents(comuRegisterPostReq.getContents());
        board.setReg_date(comuRegisterPostReq.getReg_date());
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long no) {
        boardRepository.deleteByNo(no);
    }


    @Override
    public BoardFile fileUpload(FileBoardPostReq fileBoardPostReq, String extension) {
        BoardFile boardFile = new BoardFile();
        boardFile.setBoardno(fileBoardPostReq.getBoardno());
        boardFile.setFile(fileBoardPostReq.getFile());
        boardFile.setExtension(extension);
        return boardFileRepository.save(boardFile);
    }

    @Override
    public List<BoardJoin> boarddetail(Long no) {
        List<BoardJoin> boardJoins = boardRepositorySupport.findBoardByNo(no);
        return boardJoins;
    }


}
