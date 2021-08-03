package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.Agency;
import com.ssafy.petmily.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyupdateRe extends JpaRepository<Agency, Long> {
    Optional<Agency> findByEmail(String email);
}


