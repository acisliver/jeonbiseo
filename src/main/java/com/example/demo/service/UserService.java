package com.example.demo.service;

import com.example.demo.dto.RequestUpdateUserInfoDto;
import com.example.demo.model.Application;
import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.repository.CompareRepository;
import com.example.demo.repository.UserPreferenceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public User signUpApi(User user) {
        //회원 가입
        String rawPassword = user.getPassword(); //입력한 password
        String encPassword = encoder.encode(rawPassword); //해싱한 password
        user.setPassword(encPassword);
        user.setRole(RoleType.USER); //db의 정보 중 role만 자동적으로 입력이 되지 않기 때문에 넣어줘야 함.
        userRepository.save(user);

        //user 선호도 초기화
//        int initPreference = 1;
//        List<Application> numberOfApplication = compareRepository.findAll();
//        for(int i=0;i<numberOfApplication.size(); i++){
//            System.out.println("apllication"+i);
//            userPreferenceRepository.insertPreferenceOfItem(user.getId(),
//                    numberOfApplication.get(i).getId(), initPreference);
//        }

        return user;

    }

    @Transactional
    public User updateUserInfo(RequestUpdateUserInfoDto requestUpdateUserInfoDto, int id) {

        User persistanceUser = userRepository.searchIdQuery(id).orElseThrow(() -> {
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = requestUpdateUserInfoDto.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistanceUser.setPassword(encPassword);
        persistanceUser.setNickName(requestUpdateUserInfoDto.getNickName());
        return persistanceUser;
    }
}
