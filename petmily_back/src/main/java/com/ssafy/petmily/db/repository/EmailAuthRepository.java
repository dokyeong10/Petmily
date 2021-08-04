package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.EmailCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailCheck,Long> {
    EmailCheck findByEmail(String email);
}
