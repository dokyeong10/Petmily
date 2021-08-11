package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("ComuRegisterPostReq")
public class ComuRegisterPostReq {
//
    @ApiModelProperty(name ="번호", example = "PK")
    Long no;
    @ApiModelProperty(name="작성자 번호", example="no")
    Long userno;
    @ApiModelProperty(name="작성자 번호", example="no")
    String agencycode;
    @ApiModelProperty(name="제목", example = "title")
    String title;
    @ApiModelProperty(name="내용", example = "contents")
    String contents;
    @ApiModelProperty(name="등록날짜", example = "reg_date")
    Date reg_date;
    @ApiModelProperty(name="file", example="file")
    String files[];


}
