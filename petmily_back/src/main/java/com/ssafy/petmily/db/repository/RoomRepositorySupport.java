package com.ssafy.petmily.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.petmily.db.entity.chat.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QChatRoom qChatRoom = QChatRoom.chatRoom;
    QChatUser qChatUser = QChatUser.chatUser;
    QMessage qMessage = QMessage.message;

    public List<ChatRoom> getAllChatRooms(){
        return jpaQueryFactory.select(qChatRoom).from(qChatRoom).fetch();
    }

    public String getRoomTitle(long id){
        return jpaQueryFactory.select(qChatRoom.title).from(qChatRoom)
                .where(qChatRoom.id.eq(id)).fetchOne();
    }

    public long signin(ChatUser user){
        return jpaQueryFactory.select(qChatUser.id.count()).from(qChatUser)
                .where(qChatUser.id.eq(user.getId()),qChatUser.nickname.eq(user.getNickname())).fetchOne();
    }

//    public List<Message> getMessagesByChatroomId(long id, long idx){
//
//    }


}
