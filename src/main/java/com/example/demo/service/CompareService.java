package com.example.demo.service;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.model.Application;
import com.example.demo.model.ESApplication;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.CompareESRepository;
import com.example.demo.repository.CompareRepository;
import com.example.demo.repository.UserPreferenceRepository;
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

    @Autowired
    UserPreferenceRepository userPreferenceRepository;

//    @Transactional
//    public void saveCompare(){
//        compareESRepository.deleteAll();
//    }

    @Transactional
    public List<Application> getAll() {
        return compareRepository.findAll();
    }

    @Transactional
    public List<ESApplication> search(String word) {
        return compareESRepository.findByApplication(word,PageRequest.of(0,20));
    }

    @Transactional
    public Optional<Application> findApplication(String searchTerm, Optional<PrincipalDetails> id) {
        int plusPreference = 20;
        Optional<Application> searchItem=compareRepository.findById(Integer.parseInt(searchTerm));
       if(id.isPresent()){
           UserPreference userPreference=userPreferenceRepository.findByUserIdAndApplicationId(id.get().getUser().getId(),searchItem.get().getId());
           Optional<UserPreference> userPreferenceOptional=Optional.ofNullable(userPreference);
           if(!userPreferenceOptional.isPresent()){
               UserPreference makeUserPreference=new UserPreference();
               makeUserPreference.setUserId(id.get().getUser().getId());
               makeUserPreference.setApplicationId(searchItem.get().getId());
               makeUserPreference.setPreference(plusPreference);
               userPreferenceRepository.save(makeUserPreference);
           }
           else{
               userPreference.setPreference(userPreference.getPreference()+plusPreference);
           }
       }
        return searchItem;
    }
}
