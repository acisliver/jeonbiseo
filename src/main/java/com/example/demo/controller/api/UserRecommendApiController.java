package com.example.demo.controller.api;


import com.example.demo.dto.RequestUserPreferenceDto;
import com.example.demo.dto.SearchTermDto;
import com.example.demo.service.ItemRecommendService;
import com.example.demo.service.UserRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRecommendApiController {

    @Autowired
    private UserRecommendService userRecommendService;

    @PostMapping("api/user-recommend")
    public void recommend(@RequestBody RequestUserPreferenceDto requestUserPreferenceDto){
        userRecommendService.findPreferenceItem(requestUserPreferenceDto);
    }

}
