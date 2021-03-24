package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FreeBoardRepository extends JpaRepository<Board,Integer> {

    //Optional<Board> findById(int id);


    Optional<Board> deleteById(int id);

    //id, title, nickname
    //List<BoardMapping> findAllBoardMapping();
}
