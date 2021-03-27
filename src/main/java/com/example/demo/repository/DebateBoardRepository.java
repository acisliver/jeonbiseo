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
    @Query(value = "update board set good= good +1 where ?1",nativeQuery = true)
    void goodUp(int boardId);

    @Modifying
    @Query(value = "update board set notGood= notGood +1 where ?1",nativeQuery = true)
    void notGoodUp(int boardId);
}
