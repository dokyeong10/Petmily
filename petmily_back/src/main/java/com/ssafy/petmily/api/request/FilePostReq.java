package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FilePostRequest")
public class FilePostReq {
//    @ApiModelProperty(name="animalno", example="animalno")
//    Long animalno;

    @ApiModelProperty(name="file", example="file")
   String files[];
}
