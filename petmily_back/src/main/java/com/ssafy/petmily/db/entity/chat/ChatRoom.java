package com.ssafy.petmily.db.entity.chat;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//@Getter
//@Setter
//public class ChatRoom {
//    private String roomId;
//    private String name;
//    // WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션
//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    public static ChatRoom create(String name){
//        ChatRoom room = new ChatRoom();
//        room.roomId = UUID.randomUUID().toString();
//        room.name = name;
//        return room;
//    }
//
//}

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long masterId;

    @Builder
    public ChatRoom(long no, String title, long masterId) {
        super();
        this.id = no;
        this.title = title;
        this.masterId = masterId;
    }

}
