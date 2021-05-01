package com.github.ruslanmn.springbootchat.controllers;

import com.github.ruslanmn.springbootchat.controllers.dto.Message;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public void sendMessage(Authentication authentication, String id) {
        return ;//"chat";
    }
}
