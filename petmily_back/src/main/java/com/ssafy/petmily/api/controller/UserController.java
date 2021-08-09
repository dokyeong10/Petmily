package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.LikeRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.api.response.AgencyRes;
import com.ssafy.petmily.api.response.UserRes;
import com.ssafy.petmily.api.service.AnimalService;
import com.ssafy.petmily.api.service.UserService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.auth.SsafyUserDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.animal.AnimalLike;
import com.ssafy.petmily.db.entity.user.User;
import com.ssafy.petmily.db.entity.user.UserJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	AnimalService animalService;

	@PostMapping("/personal")
	@ApiOperation(value = "개인 회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@PostMapping("/agency")
	@ApiOperation(value = "기관 회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> agencyregister(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) AgencyRegisterPostReq registerInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Agency agency =userService.createAgency(registerInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@GetMapping("/personal/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String email = userDetails.getUsername();
		User user = userService.getUserByEmailAndType(email);

		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@GetMapping("/agency/me")
	@ApiOperation(value = "기관 정보 조회", notes = "로그인한 기관 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<AgencyRes> getAgencyInfo(@ApiIgnore Authentication authentication) {

		SsafyAgencyDetails userDetails = (SsafyAgencyDetails)  authentication.getDetails();
		String email = userDetails.getUsername();
		Agency agency = userService.getAgencyByEmail(email);
		return ResponseEntity.status(200).body(AgencyRes.of(agency));
	}

	//개인회원삭제
	@DeleteMapping("/personal/{email}")
	@ApiOperation(value = "개인회원삭제", notes = "개인회원삭제")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<? extends BaseResponseBody>  deleteuser(@PathVariable String email){
		userService.deleteUser(email);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	//기관회원 삭제
	@DeleteMapping("/agency/{email}")
	@ApiOperation(value = "기관 삭제", notes = "기관삭제")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<? extends BaseResponseBody> deleteagency(@PathVariable String email){
		userService.deleteAgency(email);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	//개인회원수정
	@PatchMapping("/personal/{email}")
	public ResponseEntity<User> patchUser(@PathVariable String email,  @RequestBody User user){
		User updateUser = userService.patchUser(email,user);
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}

	//기관회원수정
	@PatchMapping("/agency/{email}")
	public ResponseEntity<Agency> patchAgency(@PathVariable String email,  @RequestBody Agency agency){
		Agency updateAgency = userService.patchAgency(email,agency);
		return new ResponseEntity<Agency>(updateAgency,HttpStatus.OK);
	}

	@ApiOperation(value = "비밀번호 찾기 -> 비밀번호 변경", notes = "비밀번호 찾기 중 이메일 인증 후 새로운 비밀번호 입력")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PatchMapping("/findpwd/change")
	public ResponseEntity<? extends BaseResponseBody>changePassword(@ApiParam(value = "비밀번호 변경하는 이메일") @RequestBody User user){
		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println(user);
		boolean isOk = userService.patchPassword(email, password);
		if(isOk){
			return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
		}else{
			return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Internal Server Error"));
		}
	}

	//즐겨찾기 등록
	@PostMapping("/like")
	@ApiOperation(value = "개인 회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> Like(@RequestBody LikeRegisterPostReq likeRegisterPostReq){
		//AnimalWait animalWait = animalService.likes(animal, user);
		AnimalLike animalLike = userService.addlike(likeRegisterPostReq);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Sucess"));
	}

	// 토큰으로 유저 mypage 조회
	@GetMapping("/mypage")
	public ResponseEntity<UserJoin>searchLike(@ApiIgnore Authentication authentication){
		System.out.println(authentication.toString());
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		long no = userDetails.getNo();
		UserJoin user = userService.searchMypage(no);

		return new ResponseEntity<UserJoin>(user, HttpStatus.OK);
	}

	//즐겨찾기 취소
	@DeleteMapping("/like/{no}")@ApiOperation(value = "즐겨찾기 삭제", notes = "즐겨찾기 삭제")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<? extends BaseResponseBody> deleteLike(@PathVariable Long no) {
		boolean isOk = userService.deleteLike(no);
		if (isOk) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}else{
			return ResponseEntity.status(401).body(BaseResponseBody.of(500,"즐겨찾는 동물 X"));
		}
	}

}
