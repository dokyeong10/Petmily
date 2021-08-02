package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AgencyRegisterPostRequest")
public class AgencyRegisterPostReq {
    @ApiModelProperty(name="유저 ID", example="ssafy_web")
    String userid;
    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;
    @ApiModelProperty(name="유저 email", example = "your_email")
    String email;
    @ApiModelProperty(name="유저 phone", example = "your_phone")
    String phone;
    @ApiModelProperty(name="유저 username", example = "your_username")
    String username;
    @ApiModelProperty(name="유저 img", example = "your_img")
    String img;
    @ApiModelProperty(name="agency_name", example = "agency_name")
    String agencyname;
    @ApiModelProperty(name="agency_code", example = "agency_code")
    String agencycode;
}
