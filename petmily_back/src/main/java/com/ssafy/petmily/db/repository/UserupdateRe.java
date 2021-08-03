package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserupdateRe extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
