package com.example.websocketproject.config.handler;

import com.example.websocketproject.domain.dto.ChatMessageDTO;
import com.example.websocketproject.domain.dto.ChatRoom;
import com.example.websocketproject.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 소켓 통신은 서버와 클라이언트가 1:N 관계를 맺는다. 즉, 하나의 서버에 다수 클라이언트가 접속할 수 있다.
 * 따라서 서버는 다수의 클라이언트가 보낸 메세지를 처리할 핸들러가 필요하다.
 * 텍스트 기반의 채팅을 구현해볼 것 이므로 "TextWebSocketHandler"를 상속받아서 작성 함
 * Client 로 부터 받은 메시지 -> 로그 출력 -> 인사 메시지 출력하는 역할
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    // * payload ? : 페이로드란 전송되는 데이터를 의미함.
    // 데이터를 전송할 때, Header와 META 데이터, 에러 체크 비트 등과 같은 다양한 요소들을 함께 보내 데이터 전송 효율과 안정성을 높히게 된다.
    // ***** 이 때, 보내고자 하는 데이터 자체를 의미하는 것이 페이로드이다.
    // 예를들어 택배 배송을 보내고 받을 때 *****"택배 물건"이 "페이로드"*****고 송장이나 박스 등과 같이 부가적인 것은 페이로드가 아니다.

//    {
//        "status":
//        "from":"localhost",
//        "to":"http://melonicedlatte.com/chatroom/1",
//        "method":"GET",
//        "data":{"message":"There is a cutty dog!"}
//    }
    // 위에 해당 JSON DATA 에서 페이로드는 "data" 이다. 나머지는 통신을 하는데 있어 용이하게 해주는 부가적 정보들!!!
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payLoad = message.getPayload();
        log.info("payLoad : {}" , payLoad);
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payLoad, ChatMessageDTO.class);

        ChatRoom chatRoom = chatService.findRoomById(chatMessageDTO.getRoomId());
        chatRoom.handlerAction(session, chatMessageDTO, chatService);

    }

    /**
     * Client 가 접속 시 호출 되는 메서드
     * @param session 웹 소켓 세션
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info(" 클라이언트 접속 :  {}" , session);

    }

    /**
     * Client 가 접속 해제 시 호출 되는 메서드
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info(" 클라이언트 접속 해제 : {} ", session);

    }
}
