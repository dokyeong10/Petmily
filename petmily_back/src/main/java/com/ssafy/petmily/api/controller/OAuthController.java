package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.common.auth.dto.SessionUser;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.config.customAnnotation.LoginUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@CrossOrigin("*")
@Controller
public class OAuthController {
    private final HttpSession httpSession;

    @GetMapping("/")
    @ApiOperation(value = "소셜 로그인", notes = "<strong>소셜 로그인</strong>을 통해 로그인 및 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    // LoginUser 라는 커스텀 어노테이션을 만들어 바로 Session에 있는 "userinfo" 정보를 가져옴
    public ResponseEntity<? extends BaseResponseBody> authController(@LoginUser SessionUser user){
        if(user !=null) {
            System.out.println("======================== 로그인 성공 ==========================" + user.getEmail() + " "+ user.getName() + " " + user.getType());
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Login Success"));
        }else{
            System.out.println("========================== 로그인 실패! ==============================");
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(401, "Login fail"));
    }
}
