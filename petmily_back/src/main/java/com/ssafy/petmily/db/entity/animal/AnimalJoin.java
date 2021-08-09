package com.ssafy.petmily.db.entity.animal;

import com.ssafy.petmily.db.entity.shelter.Shelter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "animal")
public class AnimalJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;
    String type;
    String species;
    String find_addr;
    int age;
    boolean sex;
    boolean neutered;
    Date find_date;
    String agencycode;
    String profile_img;
    String text;


    @ManyToOne
    @JoinColumn(name="agencycode",referencedColumnName = "agencycode",insertable = false, updatable = false)
    Shelter shelter;

}
