package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.community.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
    Optional<BoardFile> findByNo(Long no);
}
