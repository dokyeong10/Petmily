package com.ssafy.petmily.api.service.chat;



import com.ssafy.petmily.db.entity.chat.ChatRoom;
import com.ssafy.petmily.db.repository.RoomRepository;
import com.ssafy.petmily.db.repository.RoomRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService implements IChatRoomService {
	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomRepositorySupport roomRepositorySupport;

	@Override
	public long createRoom(ChatRoom newRoom) {
		roomRepository.save(newRoom);
		return newRoom.getId();
	}

	@Override
	public List<ChatRoom> getAllChatRooms() {
		return roomRepositorySupport.getAllChatRooms();
	}

	@Override
	public String getRoomTitle(long id) {
		return roomRepositorySupport.getRoomTitle(id);
	}

}
