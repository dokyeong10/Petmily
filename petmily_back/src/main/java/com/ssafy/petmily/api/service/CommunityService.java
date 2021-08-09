package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;

public interface CommunityService {


    Board createBoard(ComuRegisterPostReq comuRegisterPostReq);
}
