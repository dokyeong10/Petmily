package com.ssafy.petmily.db.entity.community;

import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "board")
public class BoardJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    Long userno;
    String title;
    String contents;
    Date reg_date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userno",referencedColumnName = "no",insertable = false, updatable = false)
    User user;


    @OneToMany
    @JoinColumn(name="boardno",insertable = false, updatable = false)
    List<BoardFile> boardFiles;

}
