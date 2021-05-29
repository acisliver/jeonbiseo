package com.example.demo.service;

import com.example.demo.dto.RequestUserPreferenceDto;
import com.example.demo.model.Application;
import com.example.demo.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserRecommendService {

    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Transactional
    public void findPreferenceItem(RequestUserPreferenceDto requestUserPreferenceDto) {
        List<Application> fitItems = compareRepository.fitCriteriaItems(requestUserPreferenceDto.getOs(),
                requestUserPreferenceDto.getAppWeight(), requestUserPreferenceDto.getAppSize(),
                requestUserPreferenceDto.getUsePen(), requestUserPreferenceDto.getNetwork());

        //사용자 취향에 맞는 전자기기가 있을 때
       if(fitItems != null){

        }
    }
}
