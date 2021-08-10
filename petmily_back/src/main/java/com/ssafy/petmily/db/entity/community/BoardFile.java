package com.ssafy.petmily.db.entity.community;

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
public class BoardFile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long boardno;
    String file;
    String extension;

}
