package com.example.demo.controller.api;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PutMapping("/login")
    public ResponseDto<Integer> 로그인(@RequestBody User user){
        userService.login(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
