package com.example.demo.repository;

import com.example.demo.model.Board;
import com.example.demo.model.Debate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebateRepository extends JpaRepository<Debate,Integer> {

    //Optional<Debate> findById(int id);

    Optional<Debate> deleteById(int id);
}
