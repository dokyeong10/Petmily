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

    @GetMapping("/{word}")
    public ResponseEntity<List<Shelter>> searchShelterCode(@PathVariable String word){
        System.out.println("========================= searchShelterCode 요청 데이터 : " + word);
        List<Shelter> list = shelterService.searchShelterCode(word);
        System.out.println("========================= searchShelterCode 응답 데이터 : "+list);
        return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{code}")
    public ResponseEntity<ShelterJoin> searchShelterDetail(@PathVariable String code){
        ShelterJoin shelter = shelterService.searchShelterDetail(code);
        System.out.println("========================= searchShelterDetail 응답 데이터 : " + shelter);
        return new ResponseEntity<ShelterJoin>(shelter, HttpStatus.OK);
    }
}
