package com.example.demo.service;

import com.example.demo.model.Application;
import com.example.demo.model.ESApplication;
import com.example.demo.repository.CompareESRepository;
import com.example.demo.repository.CompareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompareService {

    @Autowired
    CompareESRepository compareESRepository;
    @Autowired
    CompareRepository compareRepository;

    @Transactional
    public void saveCompare(){
        compareESRepository.deleteAll();
    }

    @Transactional
    public List<Application> getAll() {
        return compareRepository.findAll();
    }

    @Transactional
    public List<ESApplication> search(String word) {
        return compareESRepository.findByApplication(word,PageRequest.of(0,20));
    }

    @Transactional
    public Optional<Application> findApplication(String searchTerm) {
        return compareRepository.findById(Integer.parseInt(searchTerm));
    }
}
