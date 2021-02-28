package com.example.demo.config.auth;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service //bean등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //password는 알아서 처리를 하므로 username만 DB에 있는지 확인. 이 메소드는 DB에서 아이디만 확인
    //확인이 끝났으면 SecurityConfig의 configure메소드에서 비밀번호 확인
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User principal = userRepository.findByUserName(userName)
                .orElseThrow(()->{
                    System.out.printf("PrincipalDetailService에서 호출"+"유저를찾을 수 없다");
                    return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
                });
        return new PrincipalDetail(principal); //security 세션에 유저정보(타입은 userdetails)가 저장됨
    }
}
