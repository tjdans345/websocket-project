package com.example.websocketproject.controller;

import com.example.websocketproject.domain.dto.ChatRoom;
import com.example.websocketproject.service.ChatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/api/chat")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatApiController {

    private final ChatService chatService;

//    @GetMapping("/chat")
//    public String chatGet() {
//        log.info("@ChatApiController : chat GET() " );
//        return "chat";
//    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }


}
