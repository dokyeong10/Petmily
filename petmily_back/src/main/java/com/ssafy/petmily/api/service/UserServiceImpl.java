package com.ssafy.petmily.api.service;


import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.db.entity.Agency;
import com.ssafy.petmily.db.entity.User;
import com.ssafy.petmily.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.transform.Result;
import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRe userRe;

	@Autowired
	UserupdateRe userupdateRe;

	@Autowired
	AgencyRepository agencyRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;

	@Autowired
	AgencyRepositorySupport agencyRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private Object ResultCode;

	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setEmail(userRegisterInfo.getEmail());
		user.setPhone(userRegisterInfo.getPhone());
		user.setUsername(userRegisterInfo.getUsername());
		user.setImg(userRegisterInfo.getImg());
		return userRe.save(user);
	}

	@Override
	public Agency createAgency(AgencyRegisterPostReq agencyRegisterInfo) {
		Agency agency = new Agency();
		agency.setPassword(passwordEncoder.encode(agencyRegisterInfo.getPassword()));
		agency.setEmail(agencyRegisterInfo.getEmail());
		agency.setPhone(agencyRegisterInfo.getPhone());
		agency.setUsername(agencyRegisterInfo.getUsername());
		agency.setImg(agencyRegisterInfo.getImg());
		agency.setAgencycode(agencyRegisterInfo.getAgencycode());
		agency.setAgencyname(agencyRegisterInfo.getAgencyname());
		return agencyRepository.save(agency);
	}

	@Override
	public User getUserByEmail(String email) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByEmail(email).get();
		return user;
	}


	@Override
	public Agency getAgencyByEmail(String email) {
		Agency agency = agencyRepositorySupport.findAgencyByEmail(email).get();
		return agency;
	}

	@Override
	@Transactional
	public void deleteUser(String email) {
		userRe.deleteByEmail(email);
	}

	@Override
	@Transactional
	public void deleteAgency(String email) {
		agencyRepository.deleteByEmail(email);
	}

	@Override
	public User patchUser(String email, User user) {
		final Optional<User> fetchedUser = userupdateRe.findByEmail(email);
		if(fetchedUser.isPresent()){
			if(user.getPassword() != null){
				fetchedUser.get().setPassword(user.getPassword());
			}
			if(user.getPhone() != null){
				fetchedUser.get().setPhone(user.getPhone());
			}
			if(user.getImg() != null){
				fetchedUser.get().setImg(user.getImg());
			}
			return userRe.save(fetchedUser.get());
		}
		else{
			return null;
		}
	}

//	@Override
//	public User updateUser(String email, User user) {
//		final Optional<User> fetchedUser = userRepository.findByEmail(email);
//		if(fetchedUser.isPresent()){
//			user.setEmail(email);
//			return userRepository.save(user);
//		}
//		else{
//			return null;
//		}
//
//	}




}
