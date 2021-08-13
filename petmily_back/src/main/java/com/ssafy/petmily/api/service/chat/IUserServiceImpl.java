package com.ssafy.petmily.api.service.chat;

import com.ssafy.petmily.db.entity.chat.ChatUser;
import com.ssafy.petmily.db.repository.ChatUserRepository;
import com.ssafy.petmily.db.repository.RoomRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    ChatUserRepository chatUserRepository;

    @Autowired
    RoomRepositorySupport roomRepositorySupport;

    @Override
    public long createUser(ChatUser newUser) {
      chatUserRepository.save(newUser);
        return newUser.getId();
    }

    @Override
    public boolean signin(ChatUser user) {
        // TODO Auto-generated method stub
        return roomRepositorySupport.signin(user) == 0 ? false : true;
    }
}
