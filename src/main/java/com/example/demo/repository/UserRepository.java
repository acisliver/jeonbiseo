package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = "SELECT * FROM User WHERE userName= ?1", nativeQuery = true)
    User searchUserQuery(String userName);

    @Query(value = "SELECT * FROM User WHERE id= ?1", nativeQuery = true)
    Optional<User> searchIdQuery(int id);
}
