package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    String email;
    String username;
    String phone;
    String img;
    String type;

    @Enumerated(EnumType.STRING)
    @DefaultValue("USER")
    private Role role;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;


    @Builder
    public User(String name, String email, String picture, Role role, String phone, String type){
        this.username = name;
        this.email = email;
        this.phone = phone;
        this.img = picture;
        this.role = role;
        this.type = type;
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

