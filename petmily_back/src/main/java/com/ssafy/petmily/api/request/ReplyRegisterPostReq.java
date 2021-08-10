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
    long no;
    @ApiModelProperty(name ="기관인지 일반인지?", example = "false")
    boolean isAgency;

    public boolean getIsAgency(){
        return this.isAgency;
    }

}
