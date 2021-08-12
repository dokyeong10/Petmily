package com.ssafy.petmily.api.response;

import com.ssafy.petmily.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LivePostRequest")
public class LivePostRequest extends BaseResponseBody {

    @ApiModelProperty(name="기관 코드",example="3010000" )
    String agencycode;

    public static LivePostRequest of(Integer statusCode, String message,  String agencycode){
        LivePostRequest res = new LivePostRequest();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAgencycode(agencycode);
        return res;
    }

}
