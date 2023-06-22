package com.practice.my_first_api.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String sayHelloWorld() {
        return "Hello World!";
    }

}
