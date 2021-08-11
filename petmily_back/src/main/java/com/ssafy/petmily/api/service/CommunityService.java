package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.Reply;
import com.ssafy.petmily.db.entity.community.ReplyJoin;

import java.util.List;

public interface CommunityService {


    void createAgencyRepl(String agencycode, String contents, long bno);
    void createUserRepl(long userno, String contents, long bno);
    void createAgencyReplRe(String agencycode, String contesnts, long bno, long replno);
    void createUserReplRe(long userno, String contents, long bno, long replno);
}
