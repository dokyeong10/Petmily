package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.service.ShelterService;
import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "보호소 API", tags = {"Shelter"})
@RestController
@CrossOrigin("*")
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    ShelterService shelterService;

    // 보호소 검색 (보호소 이름으로 검색)
    @GetMapping("/{word}")
    public ResponseEntity<List<Shelter>> searchShelterCode(@PathVariable String word){
        System.out.println("========================= searchShelterCode 요청 데이터 : " + word);
        List<Shelter> list = shelterService.searchShelterCode(word);
        System.out.println("========================= searchShelterCode 응답 데이터 : "+list);
        return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
    }

    // 보호소 상세 정보 (보호소 코드로 검색)
    @GetMapping("/detail/{code}")
    public ResponseEntity<ShelterJoin> searchShelterDetail(@PathVariable String code){
        ShelterJoin shelter = shelterService.searchShelterDetail(code);
        System.out.println("========================= searchShelterDetail 응답 데이터 : " + shelter);
        return new ResponseEntity<ShelterJoin>(shelter, HttpStatus.OK);
    }

    // 보호소 리스트 출력
    @GetMapping("/")
    public ResponseEntity<List<Shelter>> getAllShelter(){
        List<Shelter> list = shelterService.getAllShelter();

        return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
    }
}
