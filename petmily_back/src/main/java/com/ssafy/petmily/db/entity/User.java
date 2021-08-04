package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity{
    @Column
    String email;
    @Column
    String username;
    @Column
    String phone;
    @Column
    String img;

    @Builder.Default
    String type= "normal";

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role=Role.USER;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    public User() {
    }

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

