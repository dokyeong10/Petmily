package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class User extends BaseEntity{
    String userid;
    String email;
    String username;
    String phone;
    String img;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;


    @Builder
    public User(String name, String email, String picture, Role role, String phone){
        this.username = name;
        this.email = email;
        this.phone = phone;
        this.img = picture;
        this.role = role;
    }

    public User update(String name, String picture, String phone){
        this.username = name;
        this.img = picture;
        this.phone = phone;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey() ;
    }
}

