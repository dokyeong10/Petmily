package com.ssafy.petmily.api.request;

import lombok.Getter;

@Getter
public class EmailAuthPostReq {
    private String email;
    private String authCode;
}
