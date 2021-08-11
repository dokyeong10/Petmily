package com.ssafy.petmily.api.service;


import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.agency.AgencyJoin;
import com.ssafy.petmily.db.entity.animal.AnimalLike;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.entity.user.UserJoin;

import java.util.List;

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
	Agency patchAgency(String email, Agency agency);
	User getUserByEmailAndType(String email);
	boolean patchPassword(String email, String password);
	AnimalLike addlike(LikeRegisterPostReq likeRegisterPostReq);
	UserJoin searchMypage(Long userno);
	boolean deleteLike(Long no);
	User getUserByNo(Long no);
	AgencyJoin getAgencyByCode(String agencycode);
}
