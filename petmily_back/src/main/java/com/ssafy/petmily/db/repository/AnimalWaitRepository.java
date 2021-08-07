package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalWaitRepository extends JpaRepository<Animal,Long> {
    Optional<Animal> findByAgencycode(String agencycode);
    void deleteByNo(Long no);
    Optional<Animal> findByNo(Long no);
}
