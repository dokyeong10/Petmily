package com.ssafy.petmily.api.controller;


import com.ssafy.petmily.api.request.LiveRegisterPostReq;
import com.ssafy.petmily.api.response.MainPageRes;
import com.ssafy.petmily.api.service.AnimalService;
import com.ssafy.petmily.api.service.LiveService;
import com.ssafy.petmily.api.service.ShelterService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.Live.LiveRoom;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;
import com.ssafy.petmily.db.entity.shelter.ShelterMain;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
//http://localhost:8080/swagger-ui/


@Api(value = "메인페이지 API", tags = {"Main."})
@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class MainPageController {

	@Autowired
	LiveService liveService;

	@Autowired
	AnimalService animalService;

	@Autowired
	ShelterService shelterService;

	@GetMapping("/main")
	public ResponseEntity<MainPageRes> getMainPageData(){
		List<Animal> animals = animalService.getThreeAnimal();
		List<LiveRoom> liveRooms = liveService.getFourLiveList();
		List<ShelterMain> shelterMains = shelterService.getAllShelterMain();

		return ResponseEntity.status(200).body(MainPageRes.of(liveRooms,animals,shelterMains));
	}
}
