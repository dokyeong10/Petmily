package com.ssafy.petmily.api.service;

public interface EmailAuthService {
    Boolean sendEamilAuth(String email);
    Boolean checkEmailAuth(String email, String authCode);
    String createKey();
}
