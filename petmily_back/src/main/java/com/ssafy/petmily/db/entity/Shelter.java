package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    String agencycode;
    String agencyname;
    String closeday;
    Double lat;
    Double lng;
    String week_open_time;
    String week_end_time;
    String addr;
    String tel;
}
