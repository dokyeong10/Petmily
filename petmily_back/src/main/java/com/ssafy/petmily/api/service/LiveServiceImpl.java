package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.Live.LiveRoom;
import com.ssafy.petmily.db.repository.LiveRepository;
import com.ssafy.petmily.db.repository.LiveRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@Service
public class LiveServiceImpl implements LiveService{

    @Autowired
    LiveRepository liveRepository;

    @Autowired
    LiveRepositorySupport liveRepositorySupport;

    @Override
    public boolean createLiveRoom(String agencycode, String title, String img, String description) {
        LiveRoom live = liveRepository.findByAgencycode(agencycode);

        if(live == null){
            LiveRoom liveRoom = new LiveRoom();
            liveRoom.setAgencycode(agencycode);
            liveRoom.setDescription(description);
            liveRoom.setImg(img);
            liveRoom.setTitle(title);
            liveRoom.setStartTime(Calendar.getInstance().getTime());
            liveRepository.save(liveRoom);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<LiveRoom> getLiveList() {
        return liveRepositorySupport.getLiveList();
    }

    @Override
    public List<LiveRoom> getFourLiveList() {
        return liveRepositorySupport.getFourLiveList();
    }

    @Override
    @Transactional
    public void deleteLive(String agencycode) {
        liveRepository.deleteByAgencycode(agencycode);
    }

    @Override
    public LiveRoom livedetail(String agencycode) {
        LiveRoom liveRoom = liveRepositorySupport.findLiveByAgencycode(agencycode);
        return liveRoom;
    }
}
