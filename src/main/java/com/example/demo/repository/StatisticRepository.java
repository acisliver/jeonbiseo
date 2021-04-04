package com.example.demo.repository;

import com.example.demo.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {


    @Modifying
    @Query(value = "INSERT INTO statistic(debateId, createDate) VALUE(?1, now())",nativeQuery = true)
    int statisticSave(int debateId);
}
