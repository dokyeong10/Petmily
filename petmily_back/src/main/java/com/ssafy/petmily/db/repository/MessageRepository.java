package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
