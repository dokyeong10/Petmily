package com.ssafy.petmily.db.entity.chat;

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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private long chatroomId;
    private long senderId;
    private String senderNickname;

    @Builder
    public Message(long id, String content, long chatroomId, long senderId) {
        super();
        this.id = id;
        this.content = content;
        this.chatroomId = chatroomId;
        this.senderId = senderId;
    }

    public Message(long id, String content, long chatroomId, long senderId, String senderNickname) {
        super();
        this.id = id;
        this.content = content;
        this.chatroomId = chatroomId;
        this.senderId = senderId;
        this.senderNickname = senderNickname;
    }

}
