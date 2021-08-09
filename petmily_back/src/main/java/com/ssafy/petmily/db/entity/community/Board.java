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
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long userno;
    String title;
    String contents;
    Date reg_date;

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getNo() {
        return no;
    }
}
