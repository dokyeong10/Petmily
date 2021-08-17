package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileBoardPostRequest")
public class BoardSearchPostReq {

    @ApiModelProperty(name="userno", example="10")
    long userno;
    @ApiModelProperty(name="word", example="수정")
    String word;
}
