package com.ssafy.petmily.common.auth.dto;

import com.ssafy.petmily.db.entity.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 세션에 저장하려면 직렬화를 해야 하는데
 * User 엔티티는 추후 변경사항이 있을 수 있기 때문에
 * 직렬화를 하기 위한 별도의 SessionUser 클래스 생성
 */

@Getter
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 178630l;

    private String name, email, picture;
    private Long id;

    public SessionUser(User user) {
        System.out.println("============================="+user+"====================================");
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.picture = user.getImg();
        this.id = user.getNo();
    }
}