package com.ssafy.petmily.api.service;

import com.ssafy.petmily.common.auth.dto.SessionUser;
import com.ssafy.petmily.db.entity.util.OAuthAttributes;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.repository.UserRepository;
import com.ssafy.petmily.db.repository.UserRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드, 구글, 네이버, 카카오 등을 구분하기 위한 코드
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        // oauth2 로그인 진행 시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuthAttributes : attribute(서비스 코드, 키값, 회원 이름, 이메일, 프로필 사진)를 담을 클래스 (개발자가 직접 생성)
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        System.out.println("userRequest : "+userRequest.getAccessToken().getTokenValue()); // 코드 토큰 유저정보
        System.out.println("userRequest : "+userRequest.getClientRegistration()); // 코드 토큰 유저정보

        // SessionUser : 세션에 사용자 정보를 저장하기 위한 DTO 클래스 (개발자가 생성)
        httpSession.setAttribute("userinfo", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 우리 테이블에 없는 정보면 insert를 하고 이미 존재하는 유저면 update를 한다.
    // update 하는 이유는 사용자의 정보가 바뀔 경우가 존재할 수 있기 때문 (회원 이름 or 사진 변경의 경우)
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepositorySupport.findUserByEmailAndType(attributes.getEmail(), attributes.getRegistrationId())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture(), attributes.getPhone()))
                .orElse(attributes.toEntity());

        // db에 저장 (User 정보)
        return userRepository.save(user);
    }
}
