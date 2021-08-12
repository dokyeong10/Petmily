package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<ChatRoom, Long> {

}
