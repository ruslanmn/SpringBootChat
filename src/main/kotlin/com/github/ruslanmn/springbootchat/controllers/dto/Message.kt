package com.github.ruslanmn.springbootchat.controllers.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

data class Message (
    val username: String,
    val text: String
)