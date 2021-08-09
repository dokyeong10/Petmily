package com.ssafy.petmily.api.request;

import com.ssafy.petmily.db.entity.animal.AnimalFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("AnimalRegisterPostRequest")
public class AnimalRegisterPostReq {
    @ApiModelProperty(name ="종류", example = "강아지")
    String type;
    @ApiModelProperty(name ="종", example = "진돗개")
    String species;
    @ApiModelProperty(name ="발견장소", example = "서울시 강남구")
    String find_addr;
    @ApiModelProperty(name ="나이", example = "3")
    int age;
    @ApiModelProperty(name ="성별", example = "1(남)")
    boolean sex;
    @ApiModelProperty(name ="중성화", example = "1(유)")
    boolean neutered;
    @ApiModelProperty(name ="발견날짜", example = "2021.02.12")
    Date find_date;
    @ApiModelProperty(name ="보호소코드", example = "10303")
    String agencycode;
    @ApiModelProperty(name ="이미지", example = "10303")
    String profile_img;
    @ApiModelProperty(name ="특이사항", example = "10303")
    String text;
    @ApiModelProperty(name ="특이사항", example = "10303")
    List<AnimalFile> animalFiles;



}
