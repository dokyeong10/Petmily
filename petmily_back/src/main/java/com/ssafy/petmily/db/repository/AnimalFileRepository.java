package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalFileRepository extends JpaRepository<AnimalFile, Long> {
    Optional<AnimalFile> findByNo(Long no);
}
