package com.ssafy.petmily.api.service;


import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.db.entity.Agency;
import com.ssafy.petmily.db.entity.User;

import javax.xml.transform.Result;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	Agency createAgency(AgencyRegisterPostReq agencyRegisterInfo);
	User getUserByEmail(String email);
	Agency getAgencyByEmail(String email);
	void deleteUser(String email);
	void deleteAgency(String email);
	User patchUser(String email, User user);
}
