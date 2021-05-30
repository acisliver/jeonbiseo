package com.example.demo.controller.api;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.RequestUserPreferenceDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import com.example.demo.dto.SearchTermDto;
import com.example.demo.model.Application;
import com.example.demo.model.UserPreference;
import com.example.demo.service.ItemRecommendService;
import com.example.demo.service.UserRecommendService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.elasticsearch.client.ml.job.config.DataDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserRecommendApiController {

    @Autowired
    private UserRecommendService userRecommendService;

    @PostMapping("api/user-recommend")
    public void recommend(@RequestBody RequestUserPreferenceDto requestUserPreferenceDto,
                        @AuthenticationPrincipal PrincipalDetails principalDetails){
        userRecommendService.findPreferenceItem(requestUserPreferenceDto, principalDetails.getUser().getId());


    }

    //MySql의 데이터를 csv파일로 만든 후 data 디렉토리에 저장
    @GetMapping("api/export")
    public void dataToCSV() {
        try {
            userRecommendService.dataToCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/user_recommend")
    public List<RecommendedItem> userRecommender() {
        List<RecommendedItem> recommendations = null;
        try {
            recommendations = userRecommendService.userRecommender();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendations;
    }

}
