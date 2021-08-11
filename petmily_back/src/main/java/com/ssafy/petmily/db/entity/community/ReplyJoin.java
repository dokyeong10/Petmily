package com.ssafy.petmily.db.entity.community;

import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reply")
public class ReplyJoin {
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

    @ManyToOne
    @JoinColumn(name = "userno", updatable = false, insertable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "agencycode", updatable = false, insertable = false)
    Agency agency;

    public void updateGroup(long no){
        this.grp = no;
    }
}
