package com.ssafy.petmily.api.response;

import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.animal.AnimalLike;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AnimalLikePostResponse")
public class AnimalLikePostResponse extends BaseResponseBody {

    @ApiModelProperty(name="기관 코드",example="3010000" )
    Long no;

    public static AnimalLikePostResponse of(Integer statusCode, String message, Long no){
        AnimalLikePostResponse res = new AnimalLikePostResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setNo(no);
        return res;
    }


}
