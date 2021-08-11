package com.ssafy.petmily.db.entity.shelter;

import com.ssafy.petmily.db.entity.agency.AgencyJoin;
import com.ssafy.petmily.db.entity.agency.AgencyMain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "shelter")
public class ShelterMain {
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
    @OneToOne(mappedBy = "shelterMain")
    AgencyMain agencyMain;
}
