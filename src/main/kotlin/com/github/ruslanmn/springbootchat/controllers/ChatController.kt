package com.github.ruslanmn.springbootchat.controllers

import com.github.ruslanmn.springbootchat.controllers.dto.Message
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import kotlin.Throws
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import lombok.AllArgsConstructor
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.util.HtmlUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller

@Controller
class ChatController {
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    fun sendMessage(authentication: Authentication, message: String): Message {
        return Message(authentication.name, HtmlUtils.htmlEscape(message))
    }
}