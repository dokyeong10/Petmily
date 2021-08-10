package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.community.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
