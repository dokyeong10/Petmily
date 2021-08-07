package com.ssafy.petmily.db.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

    //스프링 시큐리티에서는 권한 코드에 항상 ROLE_ 가 앞에 있어야만 합니다.
}
