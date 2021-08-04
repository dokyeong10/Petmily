package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
public class AnimalWait extends BaseEntity {

    String type;
    String species;
    String addr;
    int age;
    boolean sex;
    boolean neutered;
    String find_date;
    String agencycode;

//    @ManyToOne(optional = false)
//    @JoinColumn(name="animalWait_no")
//    Agency agency;
}
