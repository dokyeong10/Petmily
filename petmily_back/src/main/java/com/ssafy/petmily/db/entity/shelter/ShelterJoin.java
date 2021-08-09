package com.ssafy.petmily.db.entity.shelter;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.petmily.db.entity.agency.AgencyJoin;
import com.ssafy.petmily.db.entity.animal.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shelter")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class ShelterJoin implements Serializable {
    @Id
    String agencycode;

    String agencyname;
    String closeday;
    Double lat;
    Double lng;
    String week_open_time;
    String week_end_time;
    String addr;
    String tel;

    // 나를 참조하는 테이블과 join
    @OneToOne(mappedBy = "shelterJoin")
    AgencyJoin agencyJoin;

    @OneToMany
    @JoinColumn(name = "agencycode",referencedColumnName = "agencycode",insertable = false, updatable = false)
    List<Animal> animals;
}
