package com.ssafy.petmily.db.repository;

import com.ssafy.petmily.db.entity.Live.LiveRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveRepository extends JpaRepository<LiveRoom, Long> {
    LiveRoom findByNo(long no);
}
