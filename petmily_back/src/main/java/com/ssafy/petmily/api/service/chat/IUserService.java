package com.ssafy.petmily.api.service.chat;

import com.ssafy.petmily.db.entity.chat.ChatUser;

public interface IUserService {

	long createUser(ChatUser newUser);
	boolean signin(ChatUser user);

}
