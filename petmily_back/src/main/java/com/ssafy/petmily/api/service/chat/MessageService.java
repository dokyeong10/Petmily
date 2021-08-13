package com.ssafy.petmily.api.service.chat;



import com.ssafy.petmily.db.entity.chat.Message;
import com.ssafy.petmily.db.repository.MessageRepository;
import com.ssafy.petmily.db.repository.RoomRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class MessageService implements IMessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	RoomRepositorySupport roomRepositorySupport;

	@Override
	public void insertMessage(Message chatMessage) {
		messageRepository.save(chatMessage);
	}

//	@Override
//	public List<Message> getMessagesByChatroomId(long id, long idx) {
//		return messageMapper.getMessagesByChatroomId(id, idx);
//	}

}
