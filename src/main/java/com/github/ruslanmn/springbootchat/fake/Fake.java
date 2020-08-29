package com.github.ruslanmn.springbootchat.fake;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
public class Fake {
    @GetMapping
    public String helloWorld() {
        return "Hello world";
    }
}
