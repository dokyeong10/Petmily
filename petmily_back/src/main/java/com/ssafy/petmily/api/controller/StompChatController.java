//package com.ssafy.petmily.api.controller;
//
//import com.ssafy.petmily.db.entity.chat.ChatMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class StompChatController {
//
//    // 특정 Broker로 메세지를 전달
//    private final SimpMessagingTemplate template;
//
//    // Client가 Send할 수 있는 경로
//    // stompConfig에서 설정한 applicationDestinationPrefix와 @MessageMapping 경로가 병합됨
//    // "/pub/chat/enter" 로 발행 요청
//    @MessageMapping(value = "/chat/enter")
//    public void enter(ChatMessage message){
//        message.setMessage(message.getWriter() + "님이 채팅방에 입장하였습니다.");
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//
//    // "/sub/chat/room/[roomId]" 로 메세지가 전송
//    // 해당 주소는 채팅방을 구분하는 값
//    @MessageMapping(value = "/chat/message")
//    public void message(ChatMessage message){
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//}
