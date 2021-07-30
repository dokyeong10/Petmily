package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Agency extends User{
    String agencycode;
    String agencyname;
}
