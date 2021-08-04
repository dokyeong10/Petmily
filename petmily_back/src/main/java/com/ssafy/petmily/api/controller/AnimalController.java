package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.AgencyRegisterPostReq;
import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.api.service.AnimalService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.AnimalAdopt;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "동물 API", tags = {"Animal"})
@RestController
@CrossOrigin("*")
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @PostMapping("/register")
    @ApiOperation(value = "동물 등록", notes = "동물을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="동물 정보", required = true) AnimalRegisterPostReq registerInfo, AgencyRegisterPostReq register) {
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        AnimalWait animalWait = animalService.createAnimal(registerInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


}
