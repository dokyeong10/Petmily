package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.util.EmailCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailCheck,Long> {
    EmailCheck findByEmail(String email);
}
