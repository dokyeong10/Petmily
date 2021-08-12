package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.api.request.LiveRegisterPostReq;
import com.ssafy.petmily.api.response.LivePostRequest;
import com.ssafy.petmily.api.service.LiveService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.Live.LiveRoom;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
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

	// 라이브 룸 개설
	@PostMapping("/register")
	public ResponseEntity<? extends BaseResponseBody> createLiveRoom(@ApiIgnore Authentication authentication, @RequestBody LiveRegisterPostReq liveRegisterPostReq) {

		SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
		String agencycode = agencyDetails.getAgencycode();
		String title = liveRegisterPostReq.getTitle();
		String description = liveRegisterPostReq.getDescription();;
		String img = liveRegisterPostReq.getImg();
		System.out.println("======================= 라이브룸 개설 = " + "agencycode : " + agencycode + " | title : " + title + " | description : " + description + " | img : " + img);

		boolean isExist = liveService.createLiveRoom(agencycode, title, img, description);

		if(isExist){
			return ResponseEntity.status(200).body(LivePostRequest.of(200,"Success",agencycode));
		}
		return ResponseEntity.status(500).body((BaseResponseBody.of(500,"Already Exist Room")));

	}

	// 라이브룸 리스트 조회
	@GetMapping("/")
	public ResponseEntity<List<LiveRoom>> getLiveList(){
		List<LiveRoom> list = liveService.getLiveList();

		return new ResponseEntity<List<LiveRoom>>(list, HttpStatus.OK);
	}



}
