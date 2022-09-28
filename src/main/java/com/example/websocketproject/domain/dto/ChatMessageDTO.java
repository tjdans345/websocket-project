package com.example.websocketproject.domain.dto;

import lombok.*;
import org.apache.logging.log4j.message.Message;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDTO {

    /**
     * ENTER : 처음 입장
     * TALK : 채팅중인 상태 ( 이미 입장 완료 )
     */
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    @Builder
    public ChatMessageDTO(MessageType type, String roomId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }





}
