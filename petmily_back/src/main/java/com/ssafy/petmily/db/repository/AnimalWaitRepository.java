package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.AnimalWait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalWaitRepository extends JpaRepository<AnimalWait,Long> {
    Optional<AnimalWait> findByAgencycode(String agencycode);
    void deleteByNo(Long no);
    Optional<AnimalWait> findByNo(Long no);
}
