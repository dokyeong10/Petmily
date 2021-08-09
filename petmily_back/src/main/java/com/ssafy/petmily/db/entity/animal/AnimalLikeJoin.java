package com.ssafy.petmily.db.entity.animal;

import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.entity.user.UserJoin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
