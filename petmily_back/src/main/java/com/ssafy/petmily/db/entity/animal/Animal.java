package com.ssafy.petmily.db.entity.animal;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Animal {
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
  //  MultipartFile[] fileup;

//    @ManyToOne(fetch = FetchType.LAZY)
//    Agency agency;


}