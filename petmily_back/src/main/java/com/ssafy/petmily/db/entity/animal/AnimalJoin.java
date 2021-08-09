package com.ssafy.petmily.db.entity.animal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


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


    @ManyToOne
    @JoinColumn(name="agencycode",referencedColumnName = "agencycode",insertable = false, updatable = false)
    Shelter shelter;

}
