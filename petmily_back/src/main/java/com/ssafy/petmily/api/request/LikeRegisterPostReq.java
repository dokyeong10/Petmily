package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LikeRegisterPostRequest")
public class LikeRegisterPostReq {
//
    @ApiModelProperty(name="no", example = "1")
    Long no;

    @ApiModelProperty(name="animalno", example = "123")
    Long animalno;

    @ApiModelProperty(name="userno", example = "12")
    Long userno;

}
