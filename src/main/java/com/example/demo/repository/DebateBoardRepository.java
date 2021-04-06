package com.example.demo.repository;

import com.example.demo.model.Debate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DebateBoardRepository extends JpaRepository<Debate,Integer> {

    //Optional<Debate> findById(int id);

    Optional<Debate> deleteById(int id);

    @Modifying
    @Query(value = "update debate set goodNum= goodNum +1 where id = ?1",nativeQuery = true)
    void goodNum(int boardId);

    @Modifying
    @Query(value = "update debate set badNum = badNum +1 where id = ?1",nativeQuery = true)
    void badNum(int boardId);
}
