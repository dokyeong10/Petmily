package com.ssafy.petmily.api.service;


import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.agency.AgencyJoin;
import com.ssafy.petmily.db.entity.animal.AnimalLike;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.entity.user.UserJoin;
import com.ssafy.petmily.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	UserRepositorySupport userRepositorySupport;

	@Autowired
	AgencyRepositorySupport agencyRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AnimalLikeRepository animalLikeRepository;

	@Autowired
	AnimalWaitRepositorySupport animalWaitRepositorySupport;

	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setEmail(userRegisterInfo.getEmail());
		user.setPhone(userRegisterInfo.getPhone());
		user.setUsername(userRegisterInfo.getUsername());
		user.setImg(userRegisterInfo.getImg());
		return userRepository.save(user);
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
	public User getUserByEmailAndType(String email) {
		User user = userRepositorySupport.checkUserByEmailAndType(email,"normal");
		return user;
	}

	@Override
	public Agency getAgencyByEmail(String email) {
		Agency agency = agencyRepositorySupport.checkAgencyByEmail(email);
		return agency;
	}

	@Override
	@Transactional
	public void deleteUser(String email) {
		userRepositorySupport.deleteByEmailAndType(email,"normal");
	}

	@Override
	@Transactional
	public void deleteAgency(String email) {
		agencyRepository.deleteByEmail(email);
	}

	@Override
	public User patchUser(String email, User user) {
		final Optional<User> fetchedUser = userRepositorySupport.findUserByEmailAndType(email,"normal");
		if(fetchedUser.isPresent()){
			if(user.getPassword() != null){
				fetchedUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
			}
			if(user.getPhone() != null){
				fetchedUser.get().setPhone(user.getPhone());
			}
			if(user.getImg() != null){
				fetchedUser.get().setImg(user.getImg());
			}
			if(user.getType() != null){
				fetchedUser.get().setType(user.getType());
			}
			if(user.getRole() != null){
				fetchedUser.get().setRole(user.getRole());
			}
			return userRepository.save(fetchedUser.get());
		}
		else{
			return null;
		}
	}

	@Override
	public Agency patchAgency(String email, Agency agency) {
		final Optional<Agency> fetchedAgency = agencyRepository.findByEmail(email);
		if(fetchedAgency.isPresent()){
			if(agency.getPassword() != null){
				fetchedAgency.get().setPassword(passwordEncoder.encode(agency.getPassword()));
			}
			if(agency.getPhone() != null){
				fetchedAgency.get().setPhone(agency.getPhone());
			}
			if(agency.getImg() != null){
				fetchedAgency.get().setImg(agency.getImg());
			}
			if(agency.getAgencycode() != null){
				fetchedAgency.get().setAgencycode(agency.getAgencycode());
			}
			if(agency.getAgencyname() != null){
				fetchedAgency.get().setAgencyname(agency.getAgencyname());
			}
			return agencyRepository.save(fetchedAgency.get());
		}
		else{
			return null;
		}
	}

	@Override
	public boolean patchPassword(String email, String password) {
		final User fetchUser = userRepositorySupport.checkUserByEmailAndType(email,"normal");
		System.out.println(fetchUser);
		if(fetchUser != null){
			fetchUser.setPassword(passwordEncoder.encode(password));
			System.out.println(fetchUser);
			userRepository.save(fetchUser);
			return true;
		}else{
			final Agency fetchAgency = agencyRepositorySupport.checkAgencyByEmail(email);
			System.out.println(fetchAgency);
			if(fetchAgency != null){
				fetchAgency.setPassword(passwordEncoder.encode(password));
				System.out.println(fetchAgency);
				agencyRepository.save(fetchAgency);
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public AnimalLike addlike(LikeRegisterPostReq likeRegisterPostReq) {
		AnimalLike animalLike = new AnimalLike();
		animalLike.setNo(likeRegisterPostReq.getNo());
		;
		animalLike.setAnimalno(likeRegisterPostReq.getAnimalno());
		animalLike.setUserno(likeRegisterPostReq.getUserno());
		return animalLikeRepository.save(animalLike);
	}


	@Override
	public UserJoin searchMypage(Long no) {
		return userRepositorySupport.searchMypage(no);
	}

	@Override
	@Transactional
	public boolean deleteLike(Long no) {
		Optional<AnimalLike> animal = animalLikeRepository.findByNo(no);
		if (animal.isPresent()) {
			animalLikeRepository.deleteByNo(no);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User getUserByNo(Long no) {
		User user = userRepositorySupport.findUserByNo(no);
		return user;
	}

	@Override
	public AgencyJoin getAgencyByCode(String agencycode) {
		AgencyJoin agencyJoin = agencyRepositorySupport.findAgencyByCode(agencycode);
		return agencyJoin;
	}


}
