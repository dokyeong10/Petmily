package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.AnimalAdopt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalAdoptRepository extends JpaRepository<AnimalAdopt,Long> {
    Optional<AnimalAdopt> findByNo(Long no);
}
