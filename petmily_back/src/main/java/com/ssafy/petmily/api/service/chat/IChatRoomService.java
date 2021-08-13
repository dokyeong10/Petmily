package com.ssafy.petmily.api.service.chat;



import com.ssafy.petmily.db.entity.chat.ChatRoom;

import java.util.List;

public interface IChatRoomService {
	long createRoom(ChatRoom newRoom);
	List<ChatRoom> getAllChatRooms();
	String getRoomTitle(long id);
}
