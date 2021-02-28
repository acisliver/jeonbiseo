package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = "SELECT * FROM User WHERE userName= ?1 AND password= ?2", nativeQuery = true)
    User loginQuery(String userName, String password);

    //SELECT * FROM user WHERE username=?1;
    Optional<User> findByUserName(String userName);
}
