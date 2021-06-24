package com.example.demo.repository;

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

    @Query(value = "SELECT * FROM application WHERE os = ?1 and appWeight = ?2 and appSize = ?3 and usePen = ?4 and network = ?5 ", nativeQuery = true)
    List<Application> fitCriteriaItems(String os, String appWeight, String appSize, String usePen, String network);

    List<Application> findByOs(String os);

    List<Application> findByAppWeight(String appWeight);

    List<Application> findByAppSize(String appSize);

    List<Application> findByusePen(String usePen);

    List<Application> findByNetwork(String network);
}
