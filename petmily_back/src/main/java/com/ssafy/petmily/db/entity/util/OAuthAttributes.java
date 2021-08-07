package com.ssafy.petmily.db.entity.util;

import com.ssafy.petmily.db.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

// 구글 로그인 이후 가져온 사용자의 이메일, 이름, 프로필 사진 주소를 저장하는 DTO

@Getter
@ToString
public class OAuthAttributes {

    private Map<String,Object> attributes;
    private String nameAttributeKey, name, email, picture,phone, registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String phone, String registrationId){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
        this.registrationId = registrationId;

    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String,Object> attributes){

        switch (registrationId){
            case "naver":
                return ofNaver(registrationId,"id",attributes);
            case "kakao" :
                return ofKakao(registrationId,"id", attributes);
        }
        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object>attributes){
        Map<String, Object> response = (Map<String,Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .phone((String) response.get("mobile"))
                .registrationId(registrationId)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        profile.put("id", attributes.get("id"));
        profile.put("email", kakaoAccount.get("email"));
        profile.put("name", profile.get("nickname"));
        profile.put("picture", profile.get("profile_image_url"));

        return OAuthAttributes.builder().name((String) profile.get("name"))
                                        .email((String) profile.get("email"))
                                        .picture((String) profile.get("picture"))
                                        .registrationId(registrationId)
                                        .attributes(profile)
                                        .nameAttributeKey(userNameAttributeName)
                                        .build();

    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .phone(phone)
                .role(Role.USER)
                .type(registrationId)
                .build();
    }
}
