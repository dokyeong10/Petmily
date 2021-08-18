package com.ssafy.petmily.api.response;

import com.ssafy.petmily.db.entity.Live.LiveRoom;
import com.ssafy.petmily.db.entity.animal.Animal;
import com.ssafy.petmily.db.entity.shelter.Shelter;
import com.ssafy.petmily.db.entity.shelter.ShelterMain;
import com.ssafy.petmily.db.entity.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("MainPageResponse")
public class MainPageRes {

	List<LiveRoom> liveRoomList;
	List<Animal> animalList;
	List<ShelterMain> shelterList;

	
	public static MainPageRes of(List<LiveRoom> liveRooms, List<Animal> animals, List<ShelterMain> shelters) {
		MainPageRes res = new MainPageRes();
		res.setLiveRoomList(liveRooms);
		res.setAnimalList(animals);
		res.setShelterList(shelters);
		return res;
	}
}
