package com.ssafy.petmily.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class AnimalLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long animalno;
    Long userno;
    boolean state;


    @ManyToOne
    @JoinColumn(name="animalno", insertable=false, updatable=false)
    //@JoinColumn
    AnimalWait animalWait;

    @ManyToOne
    @JoinColumn(name="userno", insertable=false, updatable=false)
    //@JoinColumn
    User user;
}
