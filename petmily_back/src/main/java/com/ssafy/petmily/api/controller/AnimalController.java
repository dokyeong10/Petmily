package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.AnimalSearchPostReq;
import com.ssafy.petmily.api.service.AnimalService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.AnimalWait;
import com.ssafy.petmily.db.repository.AnimalWaitRepositorySupport;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<? extends BaseResponseBody> Animalregister(
            @RequestBody @ApiParam(value="동물 정보", required = true) AnimalRegisterPostReq registerInfo) {
        AnimalWait animalWait = animalService.createAnimal(registerInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //동물 삭제
    @DeleteMapping("/{no}")
    @ApiOperation(value = "동물삭제", notes = "동물삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<? extends BaseResponseBody> deleteAaimal(@PathVariable Long no){
        animalService.deleteAnimal(no);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //동물 수정
    @PatchMapping("/{no}")
    public ResponseEntity<AnimalWait> patchUser(@PathVariable Long no,  @RequestBody AnimalWait animalWait){
        AnimalWait updateAnimal = animalService.patchAnimal(no,animalWait);
        return new ResponseEntity<AnimalWait>(updateAnimal,HttpStatus.OK);
    }

    // 동물 리스트 필터링
  @PostMapping("/")
    public ResponseEntity<List<AnimalWait>> searchAll(@RequestBody AnimalSearchPostReq animalSearchPostReq){
        System.out.println("============================ animal list 요청 데이터 : " + animalSearchPostReq);
        String key = animalSearchPostReq.getKey();
        String word = animalSearchPostReq.getWord();
        long no = animalSearchPostReq.getNo();
        boolean isLike = animalSearchPostReq.getIsLike();
        List<AnimalWait> list = animalService.searchAllAnimal(key,word,no,isLike);
        return new ResponseEntity<List<AnimalWait>>(list, HttpStatus.OK);
  }



}
