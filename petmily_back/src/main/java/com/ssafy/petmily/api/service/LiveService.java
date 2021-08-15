package com.ssafy.petmily.api.service;

import com.ssafy.petmily.db.entity.Live.LiveRoom;

import java.util.List;

public interface LiveService {

    boolean createLiveRoom(String agencycode, String title, String img, String description);
    List<LiveRoom> getLiveList();
    List<LiveRoom> getFourLiveList();

    void deleteLive(String agencycode);

    LiveRoom livedetail(String agencycode);
}
