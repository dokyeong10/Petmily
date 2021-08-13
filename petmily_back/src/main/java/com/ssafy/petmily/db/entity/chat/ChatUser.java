package com.ssafy.petmily.db.entity.chat;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Setter
public class ChatUser {
    @Id
    private long id;
    private String nickname;

    @Builder
    public ChatUser(long id, String nickname) {
        super();
        this.id = id;
        this.nickname = nickname;
    }
}
