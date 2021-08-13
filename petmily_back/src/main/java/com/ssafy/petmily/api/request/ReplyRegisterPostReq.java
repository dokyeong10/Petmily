package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReplyRegisterPostReq")
public class ReplyRegisterPostReq {

    @ApiModelProperty(name ="댓글 내용", example = "댓글이에용")
    String contents;

    @ApiModelProperty(name ="게시글 key", example = "3")
    long bno;

    @ApiModelProperty(name ="기관 코드", example = "3010000")
    String agencycode;

    @ApiModelProperty(name ="유저 번호", example = "10")
    long userno;

}
