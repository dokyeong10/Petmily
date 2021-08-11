package com.ssafy.petmily.api.service;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.db.entity.community.Board;

public interface CommunityService {


    void createAgencyRepl(String agencycode, String contents, long no);
    void createUserRepl(long userno, String contents, long no);
    void createAgencyReplRe(String agencycode, String contesnts, long no);
    void createUserReplRe(long userno, String contents, long no);
}
