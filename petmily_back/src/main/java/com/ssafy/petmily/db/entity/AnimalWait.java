package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
    Date find_date;
    String agencycode;

//    @ManyToOne(fetch = FetchType.LAZY)
//    Agency agency;


}
