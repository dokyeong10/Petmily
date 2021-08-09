package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByNo(Long no);
}
