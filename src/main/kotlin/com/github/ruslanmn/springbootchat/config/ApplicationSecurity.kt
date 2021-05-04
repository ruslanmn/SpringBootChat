package com.github.ruslanmn.springbootchat.config

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
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.Exception
import java.util.*

@Configuration
open class ApplicationSecurity : WebSecurityConfigurerAdapter() {
    @Autowired
    @Throws(Exception::class)
    fun initialize(builder: AuthenticationManagerBuilder) {
        builder.inMemoryAuthentication()
                .withUser("u1").password(passwordEncoder().encode("1")).roles("USER")
                .and()
                .withUser("u2").password(passwordEncoder().encode("2")).roles("USER")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .formLogin().defaultSuccessUrl("/chat.html", true)
                .and()
                .authorizeRequests()
                .antMatchers("/chat.html").hasRole("USER")
                .anyRequest().authenticated()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(charSequence: CharSequence): String {
                return Base64.getEncoder().encodeToString(charSequence.toString().toByteArray())
            }

            override fun matches(charSequence: CharSequence, s: String): Boolean {
                return encode(charSequence) == s
            }
        }
    }
}