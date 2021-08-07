package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnimalSearchPostReq {
    @ApiModelProperty(name ="키워드", example = "type")
    String key;
    @ApiModelProperty(name ="검색어", example = "진돗개")
    String word;
    @ApiModelProperty(name ="유저번호", example = "3")
    long no;
    @ApiModelProperty(name ="즐겨찾기 여부", example = "false")
    boolean isLike;

    public boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(boolean like) {
        isLike = like;
    }
}
