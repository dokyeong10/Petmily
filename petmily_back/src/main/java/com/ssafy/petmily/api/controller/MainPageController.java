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

	// 메인페이지에 모든 데이터 한꺼번에 전달
	@GetMapping("/main")
	public ResponseEntity<MainPageRes> getMainPageData(){
		List<Animal> animals = animalService.getThreeAnimal();
		List<LiveRoom> liveRooms = liveService.getFourLiveList();
		List<ShelterMain> shelterMains = shelterService.getAllShelterMain();

		return ResponseEntity.status(200).body(MainPageRes.of(liveRooms,animals,shelterMains));
	}

	// 메인페이지에 동물 리스트 데이터 전달
	@GetMapping("/animalList")
	public ResponseEntity<List<Animal>> getThreeAnimal(){
		List<Animal> animals = animalService.getThreeAnimal();

		return new ResponseEntity<List<Animal>>(animals, HttpStatus.OK);
	}

	@GetMapping("/liveList")
	public ResponseEntity<List<LiveRoom>> getFourLiveList(){
		List<LiveRoom> liveRooms = liveService.getFourLiveList();

		return new ResponseEntity<List<LiveRoom>>(liveRooms, HttpStatus.OK);
	}

	@GetMapping("/shelterList")
	public ResponseEntity<List<ShelterMain>> getShelterList(){
		List<ShelterMain> shelterMains = shelterService.getAllShelterMain();

		return new ResponseEntity<List<ShelterMain>>(shelterMains, HttpStatus.OK);
	}

}
