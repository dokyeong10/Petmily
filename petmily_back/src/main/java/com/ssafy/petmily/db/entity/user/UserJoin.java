package com.ssafy.petmily.db.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.petmily.db.entity.animal.AnimalLikeJoin;
import com.ssafy.petmily.db.entity.util.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class UserJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;
    @Column
    String email;
    @Column
    String username;
    @Column
    String phone;
    @Column
    String img;

    @Builder.Default
    String type = "normal";

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @OneToMany
    @JoinColumn(name="userno",referencedColumnName = "no",insertable = false, updatable = false)
    List<AnimalLikeJoin> animalLikesJoins;

}

