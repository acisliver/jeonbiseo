package com.example.demo.repository;

import com.example.demo.model.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompareRepository extends JpaRepository<Application, Integer> {

}
