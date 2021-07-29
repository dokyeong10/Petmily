package com.ssafy.petmily.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgencyDto extends UserDto{
    private String agencycode, agencyname;

    public AgencyDto(){}

    public AgencyDto(int no, String userid, String email, String username, String password, String phone, String img, String agencycode, String agencyname) {
        super(no, userid, email, username, password, phone, img);
        this.agencycode = agencycode;
        this.agencyname = agencyname;
    }
}
