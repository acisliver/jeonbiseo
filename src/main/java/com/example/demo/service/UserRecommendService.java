package com.example.demo.service;

import com.example.demo.dto.RequestUserPreferenceDto;
import com.example.demo.model.Application;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserRecommendService {

    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    public List<UserPreference> listAll() {
        return userPreferenceRepository.findAll(Sort.by("userId").ascending());
    }

    @Transactional
    public void findPreferenceItem(RequestUserPreferenceDto requestUserPreferenceDto, int userId) {
        System.out.println("dto는" + requestUserPreferenceDto);
        List<Application> fitItems = compareRepository.fitCriteriaItems(requestUserPreferenceDto.getOs(),
                requestUserPreferenceDto.getAppWeight(), requestUserPreferenceDto.getAppSize(),
                requestUserPreferenceDto.getUsePen(), requestUserPreferenceDto.getNetwork());

        System.out.println("맞는 기종" + fitItems);

        //사용자 취향에 맞는 전자기기가 있을 때
        int plusPreference = 10;
        if (fitItems != null) {
            for (int i = 0; i < fitItems.size(); i++) {
                userPreferenceRepository.increacePrefernce(plusPreference, fitItems.get(i).getId(), userId);
            }
        }
    }
}
