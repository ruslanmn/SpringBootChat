package com.github.ruslanmn.springbootchat.controllers

import com.github.ruslanmn.springbootchat.controllers.dto.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class ChatController {
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    fun sendMessage(authentication: Authentication, message: String): Message {
        return Message(authentication.name, HtmlUtils.htmlEscape(message))
    }
}