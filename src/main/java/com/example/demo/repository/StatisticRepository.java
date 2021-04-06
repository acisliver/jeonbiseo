package com.example.demo.repository;

import com.example.demo.model.Debate;
import com.example.demo.model.DebateReply;
import com.example.demo.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {


    @Modifying
    @Query(value = "Delete from statistic where debateId = ?1", nativeQuery = true)
    int deleteStatistic(int debateId);

    @Query(value = "SELECT * FROM statistic WHERE debateId= ?1", nativeQuery = true)
    Statistic search(int debateId);

    @Modifying
    @Query(value = "Update  statistic Set pros = pros+1 WHERE  debateId= ?1", nativeQuery = true)
    int increasePros(int debateId);

    @Modifying
    @Query(value = "Update  statistic Set cons = cons+1 WHERE  debateId= ?1", nativeQuery = true)
    int increaseCons(int debateId);

    @Modifying
    @Query(value = "Update  statistic Set negative = negative+1 WHERE  debateId= ?1", nativeQuery = true)
    int increaseNegative(int debateId);
}
