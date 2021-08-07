package com.ssafy.petmily.api.response;

import com.ssafy.petmily.db.entity.agency.Agency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AgencyResponse")
public class AgencyRes {

    @ApiModelProperty(name="no")
    Long no;
    @ApiModelProperty(name="email")
    String email;
    @ApiModelProperty(name="phone")
    String phone;
    @ApiModelProperty(name="username")
    String username;
    @ApiModelProperty(name="img")
    String img;
    @ApiModelProperty(name="agencyname")
    String agencyname;
    @ApiModelProperty(name= "agencycode")
    String agencycode;

    public static AgencyRes of(Agency agency) {
        AgencyRes res = new AgencyRes();
        res.setEmail(agency.getEmail());
        res.setPhone(agency.getPhone());
        res.setUsername(agency.getUsername());
        res.setImg(agency.getImg());
        res.setAgencycode(agency.getAgencycode());
        res.setAgencyname(agency.getAgencyname());
        return res;
    }

}
