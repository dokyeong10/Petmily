package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.Live.LiveRoom;
import com.ssafy.petmily.db.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class LiveServiceImpl implements LiveService{

    @Autowired
    LiveRepository liveRepository;

    @Override
    public void createLiveRoom(String agencycode, String title, String img, String description) {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setAgencycode(agencycode);
        liveRoom.setDescription(description);
        liveRoom.setImg(img);
        liveRoom.setTitle(title);
        liveRoom.setStartTime(Calendar.getInstance().getTime());
        liveRepository.save(liveRoom);
    }
}
