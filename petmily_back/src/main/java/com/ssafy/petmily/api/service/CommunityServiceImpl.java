package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.repository.CommunityRepository;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Override
    public Board createBoard(ComuRegisterPostReq comuRegisterPostReq) {
        Board board = new Board();
        board.setUserno(comuRegisterPostReq.getUserno());
        board.setTitle(comuRegisterPostReq.getTitle());
        board.setContents(comuRegisterPostReq.getContents());
        board.setReg_date(comuRegisterPostReq.getReg_date());
        return communityRepository.save(board);
    }



}
