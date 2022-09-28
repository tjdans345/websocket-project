package com.example.websocketproject.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/api")
@RestController
@Slf4j
public class ChatApiController {

    @GetMapping("/chat")
    public String chatGet() {
        log.info("@ChatApiController : chat GET() " );
        return "chat";
    }



}
