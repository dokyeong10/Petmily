package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.service.chat.IUserService;
import com.ssafy.petmily.db.entity.chat.ChatUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatUserController {
    private final IUserService userService;

    // 유저 생성
    @PostMapping("/user")
    public ResponseEntity<ChatUser> createUser(@RequestBody ChatUser newUser) {
        System.out.println(newUser.toString());
        long resultOfCreation = userService.createUser(newUser);
        if (resultOfCreation >= 0)
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 정보 확인
    @PostMapping("/login")
    public ResponseEntity<String> signinUser(@RequestBody ChatUser user) {
        boolean resultOfSignin = userService.signin(user);
        if (resultOfSignin)
            return ResponseEntity.status(HttpStatus.OK).body(user.getNickname());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
