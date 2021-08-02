package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.common.auth.dto.SessionUser;
import com.ssafy.petmily.config.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class OAuthController {
    private final HttpSession httpSession;

    @GetMapping("/")
    // LoginUser 라는 커스텀 어노테이션을 만들어 바로 Session에 있는 "userinfo" 정보를 가져옴
    public String authController(Model model, @LoginUser SessionUser user){
        if(user !=null) {
            model.addAttribute("userName", user.getName());
            System.out.println("======================== 로그인 성공 ==========================" + user.getEmail() + user.getName());
        }else{
            System.out.println("========================== 로그인 실패! ==============================");
        }

        return "index";
    }
}
