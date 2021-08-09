package com.ssafy.petmily.db.entity.animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class AnimalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long animalno;
    String file;
    String extension;

}
