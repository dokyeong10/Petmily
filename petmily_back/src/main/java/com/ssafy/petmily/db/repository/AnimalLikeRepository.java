package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.AnimalLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalLikeRepository extends JpaRepository<AnimalLike,Long> {
    Optional<AnimalLike> findByNo(Long no);
}
