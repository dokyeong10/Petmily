package com.ssafy.petmily.api.response;

import com.ssafy.petmily.db.entity.user.User;
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

	@ApiModelProperty(name="no")
	Long no;
	@ApiModelProperty(name="email")
	String email;
	@ApiModelProperty(name="phone")
	String phone;
	@ApiModelProperty(name="username")
	String username;
	@ApiModelProperty(name="img")
	String img;
	@ApiModelProperty(name="password")
	String password;
	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setNo(user.getNo());
		res.setEmail(user.getEmail());
		res.setPhone(user.getPhone());
		res.setUsername(user.getUsername());
		res.setImg(user.getImg());
		res.setPassword(user.getPassword());
		return res;
	}
}
