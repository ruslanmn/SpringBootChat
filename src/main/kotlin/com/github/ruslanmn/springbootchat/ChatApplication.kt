package com.github.ruslanmn.springbootchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}