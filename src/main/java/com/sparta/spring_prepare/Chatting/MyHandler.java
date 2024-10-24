package com.sparta.spring_prepare.Chatting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * WebSocket Handler 작성
 * 소켓 통신은 서버와 클라이언트가 1:n으로 관계를 맺는다. 따라서 한 서버에 여러 클라이언트 접속 가능
 * 서버에는 여러 클라이언트가 발송한 메세지를 받아 처리해줄 핸들러가 필요
 * TextWebSocketHandler를 상속받아 핸들러 작성
 * 클라이언트로 받은 메세지를 log로 출력하고 클라이언트로 환영 메세지를 보내줌
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyHandler extends TextWebSocketHandler {

//    private final ObjectMapper mapper;
//
//    // 현재 연결된 세션들
//    private final Set<WebSocketSession> sessions = new HashSet<>();
//
//    // 채팅 방 아이디: {session1, session2}
//    private final Map<Long,Set<WebSocketConfiguration>> chatRoomsessionMap = new HashMap<>();
//
//    // 소켓 연결 확인
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // TODO Auto-generated method stub
//        log.info("{} 연결됨", session.getId());
//        sessions.add(session);
//    }
//    // 소켓 통신 시 메세지의 전송을 다루는 부분
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payloda = message.getPayload();
//        log.info("paylod {}", payloda);
//
//        // 페이로드 -> chaMessageDto로 변환
//        ChatmessageDto chatmessageDto = mapper.readValue(payloda, ChatMessageDto.class);
//        log.info("session {}", chatmessageDto.toString());
//
//        Long chatRoomId = chatmessageDto.getChatRoomId();
//        // 메모리 상에 채팅방에 대한 세션 없으면 만들어줌
//        if (!chatRoomsessionMap)
//    }
//}

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    //최초 연결 시
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String sessionId = session.getId();
        final String enteredMessage = sessionId + "님이 입장하셨습니다.";
        sessions.put(sessionId, session);

        sessions.values().forEach((s) -> {

            try {
                if (!s.getId().equals(sessionId) && s.isOpen()) {

                    s.sendMessage(new TextMessage(enteredMessage));
                }
            } catch (IOException e) {
            }
        });
    }

    //양방향 데이터 통신할 떄 해당 메서드가 call 된다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //do something
        final String sessionId = session.getId();
        sessions.values().forEach((s) -> {

            if (!s.getId().equals(sessionId) && s.isOpen()) {
                try {
                    s.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //웹소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String sessionId = session.getId();
        final String leaveMessage = sessionId + "님이 떠났습니다.";
        sessions.remove(sessionId); //삭제

        //메세지 전송
        sessions.values().forEach((s) -> {
            try {
                s.sendMessage(new TextMessage(leaveMessage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //통신 에러 발생 시
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    private void sendMessage(String sessionId, WebSocketMessage<?> message) {
        sessions.values().forEach(s -> {
            if (!s.getId().equals(sessionId) && s.isOpen()) {
                try {
                    s.sendMessage(message);
                } catch (IOException e) {
                }
            }
        });
    }
}