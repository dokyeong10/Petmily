package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.EmailAuthPostReq;
import com.ssafy.petmily.api.request.EmailPostReq;
import com.ssafy.petmily.api.service.EmailAuthService;
import com.ssafy.petmily.api.service.UserService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.repository.AgencyRepositorySupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailAuthController {
    @Autowired
    private EmailAuthService emailAuthService;

    @Autowired
    private AgencyRepositorySupport agencyRepositorySupport;

    @Autowired
    private UserService userService;

    @PostMapping("/send/register")
    @ApiOperation(value = "이메일 인증코드 전송", notes = "<strong>이메일 인증 코드</strong>를 전송하기 위한 Controller")
    public ResponseEntity<? extends BaseResponseBody> SendEmailAuthReg(@ApiParam(value = "인증하려는 이메일") @RequestBody EmailPostReq emailPostReq){
        System.out.println("=============================== 요청 이메일 : "+emailPostReq.getEmail());
        User user = userService.getUserByEmailAndType(emailPostReq.getEmail());
        if(user != null) { // 유저가 존재할 때
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Already Exist"));
        }
        else{ // 유저가 존재하지 않을 때
            Agency agency = agencyRepositorySupport.checkAgencyByEmail(emailPostReq.getEmail());
            if(agency != null){ // 기관 유저가 존재할 때
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Already Exist"));
            }else{
                boolean isSend = emailAuthService.sendEamilAuth(emailPostReq.getEmail());
                if(isSend)
                    return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Send Success"));
                else
                    return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Send Fail"));
            }
        }
    }

    @PostMapping("/send/findpwd")
    @ApiOperation(value = "이메일 인증코드 전송", notes = "<strong>이메일 인증 코드</strong>를 전송하기 위한 Controller")
    public ResponseEntity<? extends BaseResponseBody> SendEmailAuthPwd(@ApiParam(value = "인증하려는 이메일") @RequestBody EmailPostReq emailPostReq){
        User user = userService.getUserByEmailAndType(emailPostReq.getEmail());
        if(user == null){ // 회원이 존재하지 않을 때
            Agency agency = agencyRepositorySupport.checkAgencyByEmail(emailPostReq.getEmail());
            if(agency == null){
                return ResponseEntity.status(401).body(BaseResponseBody.of(401,"User not Found"));
            }else{ // 기관 회원이 존재할 때 메일 전송
                boolean isSend = emailAuthService.sendEamilAuth(emailPostReq.getEmail());

                if(isSend)
                    return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Send Success"));
                else
                    return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Send Fail"));
            }
        }else{ // 회원이 존재할 때
            boolean isSend = emailAuthService.sendEamilAuth(emailPostReq.getEmail());

            if(isSend)
                return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Send Success"));
            else
                return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Send Fail"));
        }
    }

    @ApiOperation(value = "인증 코드 인증", notes = "<strong>이메일 인증 코드</strong>를 인증하기 위한 Controller")
    @PostMapping("/check/authcode")
    public ResponseEntity<Boolean> checkEmailAuth(@ApiParam(value = "인증하려는 이메일과 인증코드") @RequestBody EmailAuthPostReq emailAuthPostReq){
        boolean isOk = emailAuthService.checkEmailAuth(emailAuthPostReq.getEmail(),emailAuthPostReq.getAuthCode());

        if(isOk)
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    }
}
