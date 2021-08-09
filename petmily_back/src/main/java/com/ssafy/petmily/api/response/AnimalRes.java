package com.ssafy.petmily.api.response;

import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("AnimalResponse")
public class AnimalRes {

    @ApiModelProperty(name = "no")
    Long no;
    @ApiModelProperty(name = "type")
    String type;
    @ApiModelProperty(name = "age")
    int age;
    @ApiModelProperty(name = "sex")
    boolean sex;
    @ApiModelProperty(name = "species")
    String species;
    @ApiModelProperty(name = "neutered")
    boolean neutered;
    @ApiModelProperty(name = "find_date")
    Date find_date;
    @ApiModelProperty(name = "agencyname")
   String agencyname;
    @ApiModelProperty(name = "addr")
    String addr;
    @ApiModelProperty(name = "tel")
   String tel;

    public static AnimalRes of(AnimalJoin animal){
        AnimalRes res = new AnimalRes();
        res.setNo(animal.getNo());
        res.setType(animal.getType());
        res.setAge(animal.getAge());
        res.setSex(animal.isSex());
        res.setSpecies(animal.getSpecies());
        res.setNeutered(animal.isNeutered());
        res.setFind_date(animal.getFind_date());
        res.setAgencyname(animal.getShelter().getAgencyname());
        res.setAddr(animal.getShelter().getAddr());
        res.setTel(animal.getShelter().getTel());
        return res;
    }
}
