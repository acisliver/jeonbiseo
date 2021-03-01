package com.example.demo.service;

import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public User login(User user){
        return userRepository.loginQuery(user.getUserName(),user.getPassword());
    }

    @Transactional
    public void signUpApi(User user){
        user.setRole(RoleType.USER); //db의 정보 중 role만 자동적으로 입력이 되지 않기 때문에 넣어줘야 함.
        userRepository.save(user);
    }
}
