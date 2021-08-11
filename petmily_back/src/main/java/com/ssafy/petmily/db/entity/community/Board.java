package com.ssafy.petmily.db.entity.community;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long userno;
    String title;
    String contents;
    Date reg_date;
    @Builder.Default
    Long replycnt = (long) 0;

    public void updateRepl(int num){
        this.replycnt = replycnt+num;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getNo() {
        return no;
    }
}
