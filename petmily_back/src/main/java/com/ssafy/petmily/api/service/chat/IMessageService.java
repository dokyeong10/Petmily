package com.ssafy.petmily.api.service.chat;



import com.ssafy.petmily.db.entity.chat.Message;

import java.util.List;

public interface IMessageService {
	void insertMessage(Message chatMessage);
//	List<Message> getMessagesByChatroomId(long id, long idx);
}
