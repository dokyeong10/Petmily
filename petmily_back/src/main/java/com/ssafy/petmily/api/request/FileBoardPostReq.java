package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileBoardPostRequest")
public class FileBoardPostReq {
    @ApiModelProperty(name="boardno", example="boardno")
    Long boardno;
    @ApiModelProperty(name="file", example="file")
    String file;
}
