package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;
import com.ssafy.petmily.db.repository.BoardFileRepository;
import com.ssafy.petmily.db.repository.CommunityRepository;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    @Override
    public Board createBoard(ComuRegisterPostReq comuRegisterPostReq) {
        Board board = new Board();
        board.setUserno(comuRegisterPostReq.getUserno());
        board.setTitle(comuRegisterPostReq.getTitle());
        board.setContents(comuRegisterPostReq.getContents());
        board.setReg_date(comuRegisterPostReq.getReg_date());
        return communityRepository.save(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long no) {
        communityRepository.deleteByNo(no);
    }


    @Override
    public BoardFile fileUpload(FileBoardPostReq fileBoardPostReq, String extension) {
        BoardFile boardFile = new BoardFile();
        boardFile.setBoardno(fileBoardPostReq.getBoardno());
        boardFile.setFile(fileBoardPostReq.getFile());
        boardFile.setExtension(extension);
        return boardFileRepository.save(boardFile);
    }


}
