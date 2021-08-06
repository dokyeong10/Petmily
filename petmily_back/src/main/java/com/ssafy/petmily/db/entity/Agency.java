package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
// animalWait Entity에서 User와의 관계를 Json으로 변환시 오류 방지를 위한 코드
public class Agency{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;
    String email;
    String username;
    String phone;
    String img;
    String agencycode;
    String agencyname;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

}
