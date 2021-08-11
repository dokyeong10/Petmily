package com.ssafy.petmily.api.controller;


import com.auth0.jwt.JWT;
import com.nimbusds.jose.crypto.PasswordBasedDecrypter;
import com.ssafy.petmily.api.request.AgencyLoginPostReq;
import com.ssafy.petmily.api.request.PasswordFindPostReq;
import com.ssafy.petmily.api.request.UserLoginPostReq;
import com.ssafy.petmily.api.response.AgencyLoginPostRes;
import com.ssafy.petmily.api.response.UserLoginPostRes;
import com.ssafy.petmily.api.service.UserService;
import com.ssafy.petmily.common.auth.SsafyUserDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.common.util.JwtTokenUtil;
import com.ssafy.petmily.db.entity.agency.Agency;
import com.ssafy.petmily.db.entity.user.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
//http://localhost:8080/swagger-ui/

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;


	@Autowired
	PasswordEncoder passwordEncoder;


	
	@PostMapping("/personal/login")
	@ApiOperation(value = "개인 로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> userlogin(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();
		User user = userService.getUserByEmailAndType(email);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		System.out.println(user);
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(email)));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}

	@PostMapping("/agency/login")
	@ApiOperation(value = "기관 로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<AgencyLoginPostRes> agencylogin(@RequestBody @ApiParam(value="로그인 정보", required = true) AgencyLoginPostReq loginInfo) {
		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();

		Agency agency = userService.getAgencyByEmail(email);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if(passwordEncoder.matches(password, agency.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			return ResponseEntity.ok(AgencyLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(email)));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(AgencyLoginPostRes.of(401, "Invalid Password", null));
	}

	@PostMapping("/personal/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<Boolean> getUserInfo(@ApiIgnore Authentication authentication, @RequestBody PasswordFindPostReq passordfind) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		Long no = userDetails.getNo();
		User user = userService.getUserByNo(no);
		String password = passordfind.getPassword();
		if(passwordEncoder.matches(password, user.getPassword())){
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}else{
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
}
