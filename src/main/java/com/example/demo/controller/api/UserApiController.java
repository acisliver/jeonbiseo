package com.example.demo.controller.api;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseDto<Integer> 로그인(@RequestBody User user){
        User checkLogin= userService.login(user);
        if(checkLogin != null) {
            System.out.printf("로그인 완료");
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        else{
            System.out.printf("로그인 실패");
            return new ResponseDto<Integer>(HttpStatus.NO_CONTENT.value(), 0);
        }
    }

    @PostMapping("/signup")
    public ResponseDto<Integer> save(@RequestBody User user) {
        User checkSignUp = userService.signUpApi(user);
        if(checkSignUp != null){
            System.out.printf("회원가입 완료");
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        else {
            System.out.printf("회원가입 실패");
            return new ResponseDto<Integer>(HttpStatus.NO_CONTENT.value(), 0);
        }
    }
}
