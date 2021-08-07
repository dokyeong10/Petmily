package com.ssafy.petmily.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmailCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String email;
    private String authCode;

    @Builder
    public EmailCheck(String email, String authCode){
        this.email=email;
        this.authCode = authCode;
    }

    public void update(String checked){
        this.authCode = checked;
    }
}
