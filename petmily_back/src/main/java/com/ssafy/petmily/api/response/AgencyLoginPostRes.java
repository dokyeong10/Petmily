package com.ssafy.petmily.api.response;

import com.ssafy.petmily.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AgencyLoginPostResponse")
public class AgencyLoginPostRes extends BaseResponseBody {
    @ApiModelProperty(name="JWT 인증 토큰",example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN..." )
    String accessToken;

    public static AgencyLoginPostRes of(Integer statusCode, String message, String accessToken){
        AgencyLoginPostRes res = new AgencyLoginPostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);
        return res;
    }
}
