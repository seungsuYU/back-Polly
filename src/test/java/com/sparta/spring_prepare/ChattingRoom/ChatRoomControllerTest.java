package com.sparta.spring_prepare.ChattingRoom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChatRoomControllerTest {

    @Mock
    private ChatRoomService chatRoomService;

    @InjectMocks
    private ChatRoomController chatRoomController;  // ChatRoomController를 정의하고 있는지 확인

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllChatRooms() {
        // 테스트 로직 작성
        chatRoomController.getAllChatRooms();
        verify(chatRoomService).findAll();  // 서비스 메소드가 호출되는지 확인
    }

    @Test
    public void testGetChatRoomById() {
        Long testChatRoomId = 1L; // 테스트할 채팅방 ID

        // ChatRoom 객체를 두 사용자로 초기화
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(testChatRoomId);
        chatRoom.setUsers(List.of("username1", "username2")); // 두 사용자 지정

        when(chatRoomService.findById(testChatRoomId)).thenReturn(chatRoom);

        // 채팅방 정보를 가져오는 메서드 호출
        chatRoomController.getChatRoomById(testChatRoomId);

        verify(chatRoomService).findById(testChatRoomId);
    }

    // 추가적인 테스트 메소드
}
