package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;

public interface CommunityService {


    Board createBoard(ComuRegisterPostReq comuRegisterPostReq);

    void deleteBoard(Long no);

    BoardFile fileUpload(FileBoardPostReq fileBoardPostReq, String extension);
}
