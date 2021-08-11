package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReplyRegisterPostReq")
public class ReplySearchPostReq {

    @ApiModelProperty(name ="게시글 key", example = "3")
    long bno;
    @ApiModelProperty(name ="특정 댓글 번호", example = "2")
    long replno;

}
