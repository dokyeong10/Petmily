package com.ssafy.petmily.db.entity.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long bno;
    Long grp;
    Long grporder;
    Long grpdeep;
    Long userno;
    String agencycode;
    String contents;
    Date regdate;


    public void updateGroup(long no){
        this.grp = no;
    }

    public void updateContents(String input){
        this.contents = input;
    }
}
