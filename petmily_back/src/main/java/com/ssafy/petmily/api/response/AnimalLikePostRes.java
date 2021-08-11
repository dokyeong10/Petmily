package com.ssafy.petmily.api.response;

import com.ssafy.petmily.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AnimalLikePostResponse")
public class AnimalLikePostRes extends BaseResponseBody {
    @ApiModelProperty(name="좋아요 번호", example ="3")
    Long no;
    public static AnimalLikePostRes of(Integer statusCode, String message, long no) {
        AnimalLikePostRes res = new AnimalLikePostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setNo(no);
        return res;
    }



}
