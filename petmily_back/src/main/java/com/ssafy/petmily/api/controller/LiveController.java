package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.api.request.LiveRegisterPostReq;
import com.ssafy.petmily.api.service.LiveService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
//http://localhost:8080/swagger-ui/

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "라이브 API", tags = {"Live."})
@RestController
@CrossOrigin("*")
@RequestMapping("/live")
public class LiveController {

	@Autowired
	LiveService liveService;

	@PostMapping("/register")
	public ResponseEntity<? extends BaseResponseBody> createLiveRoom(@ApiIgnore Authentication authentication, @RequestBody LiveRegisterPostReq liveRegisterPostReq) {

		SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
		String agencycode = agencyDetails.getAgencycode();
		String title = liveRegisterPostReq.getTitle();
		String description = liveRegisterPostReq.getDescription();;
		String img = liveRegisterPostReq.getImg();
		System.out.println("======================= 라이브룸 개설 = " + "agencycode : " + agencycode + " | title : " + title + " | description : " + description + " | img : " + img);

		liveService.createLiveRoom(agencycode, title, img, description);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));

	}



}
