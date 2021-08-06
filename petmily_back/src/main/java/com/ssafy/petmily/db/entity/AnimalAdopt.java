package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class AnimalAdopt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;
    String type;
    String species;
    String addr;
    int age;
    boolean sex;
    boolean neutered;
    String find_date;
    String agencycode;

//    @ManyToOne(optional = false)
//    @JoinColumn(name="animalAdopt_no")
//    Agency agency;


}
