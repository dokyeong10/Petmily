package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 모델 간 공통 사항 정의.
 * 공통 Entity 속성
 * 객체들이 주로 사용하는 공통 mapping 정보를 정의
 * 객체들 간의 공통되는 속성을 정의
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no= null;
}
