package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.common.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class OAuthController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
            SessionUser user = (SessionUser) httpSession.getAttribute("userinfo");

        if(user !=null) {
            model.addAttribute("userName", user.getName());
            System.out.println("======================== 로그인 성공 ==========================" + user.getEmail() + user.getName());
        }else{
            System.out.println("로그인?");
        }

        return "index";
    }

}
