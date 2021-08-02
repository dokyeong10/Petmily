package com.ssafy.petmily.api.response;

import com.ssafy.petmily.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes {
	@ApiModelProperty(name="userid")
	String userid;
	@ApiModelProperty(name="email")
	String email;
	@ApiModelProperty(name="phone")
	String phone;
	@ApiModelProperty(name="username")
	String username;
	@ApiModelProperty(name="img")
	String img;
	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUserid(user.getUserid());
		res.setEmail(user.getEmail());
		res.setPhone(user.getPhone());
		res.setUsername(user.getUsername());
		res.setImg(user.getImg());
		return res;
	}
}
