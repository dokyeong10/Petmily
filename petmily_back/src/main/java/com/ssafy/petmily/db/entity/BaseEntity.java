package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 모델 간 공통 사항 정의.
 * 공통 Entity 속성
 * 객체들이 주로 사용하는 공통 mapping 정보를 정의
 * 객체들 간의 공통되는 속성을 정의
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no= null;
}
