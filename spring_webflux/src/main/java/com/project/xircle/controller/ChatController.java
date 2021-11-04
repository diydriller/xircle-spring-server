package com.project.xircle.controller;

import com.project.xircle.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
}
