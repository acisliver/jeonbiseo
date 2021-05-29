package com.example.demo.service;

import com.example.demo.model.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompareRepository extends JpaRepository<Application, Integer> {

    @Query(value = "SELECT id FROM application where productName = ?1", nativeQuery = true)
    int searchId(String productName);

    @Query(value = "SELECT productName FROM application WHERE id = ?1", nativeQuery = true)
    String searchApplication(int id);
}
