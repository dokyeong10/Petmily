package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.agency.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    void deleteByEmail(String email);
    Optional<Agency> findByEmail(String email);

}
