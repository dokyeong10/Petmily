package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReplyRegisterPostReq")
public class ReplyReRegisterPostReq {

    @ApiModelProperty(name ="댓글 내용", example = "댓글이에용")
    String contents;
    @ApiModelProperty(name ="게시글 key", example = "3")
    long bno;
    @ApiModelProperty(name ="기관인지 일반인지?", example = "false")
    boolean isAgency;
    @ApiModelProperty(name ="답글 대상의 댓글 번호 ", example = "4")
    long replno;

    public boolean getIsAgency(){
        return this.isAgency;
    }

}
