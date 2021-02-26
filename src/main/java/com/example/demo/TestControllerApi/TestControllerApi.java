package com.example.demo.TestControllerApi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerApi {

    @PostMapping("")
    public String test(){

        return "Connection Test";
    }
}
