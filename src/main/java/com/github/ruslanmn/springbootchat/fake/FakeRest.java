package com.github.ruslanmn.springbootchat.fake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeRest {

    @GetMapping("/fakerest")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/hello")
    public String chat(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }
}
