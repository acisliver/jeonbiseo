package com.example.demo.repository;

import com.example.demo.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreeBoardRepository extends JpaRepository<Board,Integer> {

    //Optional<Board> findById(int id);


    Optional<Board> deleteById(int id);
}
