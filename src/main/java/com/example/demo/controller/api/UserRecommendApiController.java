package com.example.demo.controller.api;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.RequestUserPreferenceDto;

import java.io.IOException;

import com.example.demo.model.Application;
import com.example.demo.model.User;
import com.example.demo.service.UserRecommendService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@EnableScheduling
@RestController
public class UserRecommendApiController {

    @Autowired
    private UserRecommendService userRecommendService;

    //
    @PostMapping("api/get-preference")
    public void recommend(@RequestBody RequestUserPreferenceDto requestUserPreferenceDto,
                        @AuthenticationPrincipal PrincipalDetails principalDetails){
        userRecommendService.getPreferenceItem(requestUserPreferenceDto, principalDetails.getUser().getId());

    }

    //MySql의 데이터를 csv파일로 만든 후 data 디렉토리에 저장
    @Scheduled(cron = "0 0/1 * * * *")
    public void dataToCSV() {
        try {
            userRecommendService.dataToCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/recommend-result")
    public Optional<Application> userRecommender(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        Optional<Application> recommendations = null;
        try {
            recommendations = userRecommendService.userRecommender(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendations;
    }

}
