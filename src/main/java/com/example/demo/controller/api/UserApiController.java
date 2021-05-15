package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.dto.RequestUpdateUserInfoDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ResponseGenericDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    UserService userService;

    @PostMapping("api/signup")
    public ResponseDto<Integer> save(@RequestBody User user) {
        logger.info("회원가입 시도");
        User checkSignUp = userService.signUpApi(user);
        if(checkSignUp != null) {
            System.out.printf("회원가입 완료");
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        else {
            System.out.printf("회원가입 실패");
            return new ResponseDto<Integer>(HttpStatus.NO_CONTENT.value(), 0);
        }
    }

    @PutMapping("/api/mypage/update")
    public ResponseGenericDto<User> updateUser(@RequestBody RequestUpdateUserInfoDto requestUpdateUserInfoDto,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){

        //db변경
        User user = userService.updateUserInfo(requestUpdateUserInfoDto, principalDetails.getUser().getId());
        if(user != null)
            return new ResponseGenericDto<User>(user, 1);
        else
            return new ResponseGenericDto<User>(null, 0);
    }

}
