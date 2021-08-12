package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.chat.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
