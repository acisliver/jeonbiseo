package com.example.demo.controller.api;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

//    @PutMapping("/login")
//    public ResponseDto<Integer> 로그인(@RequestBody User user){
//        userService.login(user);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }

    @PostMapping("/signup")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController save 호출 됨");
        System.out.printf("\nuser에서 "+user.toString());
        System.out.println("\n11111111111111111");
        userService.signUpApi(user);
        System.out.println("\n222222222222222");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
