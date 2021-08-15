package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.AnimalRegisterPostReq;
import com.ssafy.petmily.api.request.AnimalSearchPostReq;
import com.ssafy.petmily.api.service.AnimalService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
            @RequestBody @ApiParam(value = "동물 정보", required = true) AnimalRegisterPostReq registerInfo, @ApiIgnore Authentication authentication) {

        // 동물 정보 등록
        SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
        String agencycode = agencyDetails.getAgencycode();

        System.out.println("============================= agencycode : " + agencycode);

        registerInfo.setAgencycode(agencycode);
        Animal animal = animalService.createAnimal(registerInfo);

        // 파일 등록
        String files[] = registerInfo.getFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("============================ file name : " + files[i]);
        }

        for (int i = 0; i < files.length; i++) {
            String extension = "";
            String[] ext = files[i].split("\\.");
            extension = ext[(ext.length) - 1];
            System.out.println("============================ file extention : " + extension);
            AnimalFile animalfile = animalService.fileUpload(files[i], extension);
        }
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
    public ResponseEntity<? extends BaseResponseBody> deleteAaimal(@PathVariable Long no) {
        animalService.deleteAnimal(no);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //동물 수정
    @PatchMapping("/")
    public ResponseEntity<Animal> patchUser(@RequestBody AnimalRegisterPostReq animalRegisterPostReq) {
        long no = animalRegisterPostReq.getNo();
        // 파일 삭제
        animalService.deleteFile(no);
        // 파일 등록
        String files[] = animalRegisterPostReq.getFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("============================ file name : "+files[i]);
        }

        for (int i = 0; i < files.length; i++) {
            String extension = "";
            String[] ext = files[i].split("\\.");
            extension = ext[(ext.length) - 1];
            System.out.println("============================ file extention : "+ extension);
            AnimalFile animalfile = animalService.fileUpdate(no, files[i], extension);
        }

        Animal updateAnimal = animalService.patchAnimal(no, animalRegisterPostReq);
        return new ResponseEntity<Animal>(updateAnimal, HttpStatus.OK);
    }

    // 동물 리스트 필터링
    @PostMapping("/")
    public ResponseEntity<List<Animal>> searchAll(@RequestBody AnimalSearchPostReq animalSearchPostReq) {
        System.out.println("============================ animal list 요청 데이터 : " + animalSearchPostReq);
        String key = animalSearchPostReq.getKey();
        String word = animalSearchPostReq.getWord();
        long no = animalSearchPostReq.getNo();
        boolean isLike = animalSearchPostReq.getIsLike();
        List<Animal> list = animalService.searchAllAnimal(key, word, no, isLike);
        return new ResponseEntity<List<Animal>>(list, HttpStatus.OK);
    }


    @GetMapping("/details/{no}")
    public ResponseEntity<AnimalJoin> animaldetail(@PathVariable Long no) {
        AnimalJoin animalJoins = animalService.animaldetail(no);
        return new ResponseEntity<AnimalJoin>(animalJoins, HttpStatus.OK);
    }

}




