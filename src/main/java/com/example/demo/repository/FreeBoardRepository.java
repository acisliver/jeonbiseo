package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FreeBoardRepository extends JpaRepository<Board,Integer> {

    //Optional<Board> findById(int id);


    Optional<Board> deleteById(int id);

    @Modifying
    @Query(value = "update board set goodNum= goodNum +1 where id = ?1",nativeQuery = true)
    void goodNum(int boardId);

    @Modifying
    @Query(value = "update board set badNum= badNum +1 where id = ?1",nativeQuery = true)
    void badNum(int boardId);

    //id, title, nickname
    //List<BoardMapping> findAllBoardMapping();
}
