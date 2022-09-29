package com.example.websocketproject.ver1.config.websocket;

import com.example.websocketproject.ver1.config.handler.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 핸들러를 이용해 WebSocket 을 활성화하기 위한 Config
 * @EnableWebSocket 어노테이션을 사용해 WebSocket 을 활성화 하도록 한다.
 * WebSocket 에 접속하기 위한 Endpoint 는 /chat 으로 설정
 * 도메인이 다른 서버에서도 접속 가능하도록 CORS : setAllowedOrigins("*"); 를 추가해준다.
 * 클라이언트가 엔드포인트로 접근 했을 때 커넥션을 연결하고 메세지 통신을 할 수 있는 기본 준비를 마쳤다.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;


//    교차 출처 리소스 공유(Cross-Origin Resource Sharing, CORS)는 추가 HTTP 헤더를 사용해 한 출처에서 실행 중인 웹 어플리케이션이
//    다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 체제이다.
//    웹 어플리케이션은 리소스가 자신의 출처( domain, protocol, port )와 다를 때 교차 출처 HTTP 요청을 실행한다.
//    이에 대한 응답으로 서버는 Access-Control-Allow-Origin 헤더를 다시 보낸다.

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "ws/v1/api/chat").setAllowedOrigins("*");
    }





}
