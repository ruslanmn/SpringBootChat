package com.github.ruslanmn.springbootchat.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
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