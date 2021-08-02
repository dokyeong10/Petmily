package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AgencyLoginPostRequset")
public class AgencyLoginPostReq {
    @ApiModelProperty(name="유저 ID", example="userid")
    String userid;
    @ApiModelProperty(name="유저 Password", example="password")
    String password;
}
