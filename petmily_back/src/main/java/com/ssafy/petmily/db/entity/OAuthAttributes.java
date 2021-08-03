package com.ssafy.petmily.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

// 구글 로그인 이후 가져온 사용자의 이메일, 이름, 프로필 사진 주소를 저장하는 DTO

@Getter
@ToString
public class OAuthAttributes {

    private Map<String,Object> attributes;
    private String nameAttributeKey, name, email, picture,phone;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String phone){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String,Object> attributes){

        switch (registrationId){
            case "naver":
                return ofNaver("id",attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object>attributes){
        Map<String, Object> response = (Map<String,Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .phone((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .phone(phone)
                .role(Role.GUEST)
                .build();
    }
}
