package com.ssafy.petmily.db.entity.shelter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Shelter {
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

}
