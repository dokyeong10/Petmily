package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.EmailAuthPostReq;
import com.ssafy.petmily.api.request.EmailPostReq;
import com.ssafy.petmily.api.service.EmailAuthService;
import com.ssafy.petmily.common.response.BaseResponseBody;
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

    @PostMapping("/send")
    @ApiOperation(value = "이메일 인증코드 전송", notes = "<strong>이메일 인증 코드</strong>를 전송하기 위한 Controller")
    public ResponseEntity<? extends BaseResponseBody> SendEmailAuth(@ApiParam(value = "인증하려는 이메일") @RequestBody EmailPostReq emailPostReq){
        System.out.println(emailPostReq.toString());

        boolean isSend = emailAuthService.sendEamilAuth(emailPostReq.getEmail());

        if(isSend)
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Send Success"));
        else
            return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Send Fail"));
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
