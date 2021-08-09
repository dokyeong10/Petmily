package com.ssafy.petmily.db.entity.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Board {
    @Id
    Long no;

    Long userno;
    String title;
    String contents;
    Date reg_date;
    int viewcount;

}
