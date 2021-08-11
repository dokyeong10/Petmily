package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LiveRegisterPostRequest")
public class LiveRegisterPostReq {

    @ApiModelProperty(name="라이브 제목", example = "Live_title")
    String title;
    @ApiModelProperty(name="유저 라이브 설명", example = "Live_description")
    String description;
    @ApiModelProperty(name="썸네일 img", example = "Sumnail_img")
    String img;

}
