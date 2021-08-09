package com.ssafy.petmily.db.entity.animal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "animalLike")
public class AnimalLikeJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long animalno;
    Long userno;
    boolean state;


    @ManyToOne
    @JoinColumn(name="animalno", referencedColumnName = "no", insertable=false, updatable=false)
    Animal animal;


}
