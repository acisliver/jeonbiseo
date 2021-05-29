package com.example.demo.repository;

import com.example.demo.model.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Integer> {

    @Query(value = "SELECT recommendItem FROM recommend WHERE selectedItem = ?1", nativeQuery = true)
    List<Integer> searchRecommend(int getItemId);
}
