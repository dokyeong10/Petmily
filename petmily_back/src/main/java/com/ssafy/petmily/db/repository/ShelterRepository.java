package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Shelter findByNo(Long no);
}
