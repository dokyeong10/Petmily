package com.ssafy.petmily.model.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicService {

    private int no;
    private String userid, email, username, password, phone, img;

    public BasicService() {}

    public BasicService(int no, String userid, String email, String username, String password, String phone, String img) {
        this.no = no;
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.img = img;
    }
}
