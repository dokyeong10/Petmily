//package com.ssafy.petmily.db.repository;
//
//import com.ssafy.petmily.db.entity.chat.ChatRoom;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class ChatRoomRepository {
//
//    private Map<String, ChatRoom> chatRoomMap;
//
//    public List<ChatRoom> findAllRooms(){
//        // 채팅방 생성 순서 최근 순으로 반환
//        List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
//        Collections.reverse(result);
//
//        return result;
//    }
//
//    public ChatRoom findRoomById(String id){
//
//        // db에서 id로 room 조회
//        return chatRoomMap.get(id);
//    }
//
//    public ChatRoom createChatRoom(String name){
//        ChatRoom room = ChatRoom.create(name);
//
//        // db에 room 정보 저장
//        chatRoomMap.put(room.getRoomId(), room);
//
//        return room;
//    }
//}
