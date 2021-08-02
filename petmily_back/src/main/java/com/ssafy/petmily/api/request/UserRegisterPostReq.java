package com.ssafy.petmily.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {

	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 email", example = "your_email")
	String email;
	@ApiModelProperty(name="유저 phone", example = "your_phone")
	String phone;
	@ApiModelProperty(name="유저 username", example = "your_username")
	String username;
	@ApiModelProperty(name="유저 img", example = "your_img")
	String img;


}
