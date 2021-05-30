package com.example.demo.controller.api;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.RequestUserPreferenceDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import com.example.demo.dto.SearchTermDto;
import com.example.demo.model.UserPreference;
import com.example.demo.service.ItemRecommendService;
import com.example.demo.service.UserRecommendService;
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
    public void dataToCSV() throws IOException {

        ICsvBeanWriter csvWriter = null;
        try {
            List<UserPreference> userPreferenceList = userRecommendService.listAll();

            csvWriter =
                    new CsvBeanWriter(new OutputStreamWriter(new FileOutputStream("src/main/data/userpreference")), CsvPreference.STANDARD_PREFERENCE);

            String[] nameMapping = {"userId", "applicationId", "preference"};

            //사용자 개인의 preference 총합 계산
            //현재 userId
            //총 preference를 구하기 위한 초기값
            //userId와 totalPreference를 매핑해줄 Map
            Integer currentId = userPreferenceList.get(0).getUserId();
            float totalPreference = 0;
            HashMap<Integer, Float> totalPreferenceMap = new HashMap<>();

            //userPreference만큼 반복
            for (UserPreference userPreference: userPreferenceList){
                //현재 비교하는 userId와 userPreference의 userId가 같다면
                //총합에 현재 preference를 더한다
                if(currentId == userPreference.getUserId()){
                    totalPreference += userPreference.getPreference();
                }
                //userPreference의 userId가 바뀌었다면
                //totalPreference를 Map에 저장하고 currentId를 바꾼다
                //그 후 바뀐 currentId로 totalPreference 값을 초기화한다
                else{
                    totalPreferenceMap.put(currentId, totalPreference);
                    currentId = userPreference.getUserId();
                    totalPreference = userPreference.getPreference();
                }
                //마지막 값은 그대로 Map에 넣는다
                totalPreferenceMap.put(currentId, totalPreference);
            }
            System.out.println(totalPreferenceMap);

            //총 preference를 5.0기준으로 변환
            //현재 userId
            //앞에서 구한 총 preference의 최초 비교값
            currentId = userPreferenceList.get(0).getUserId();
            totalPreference = totalPreferenceMap.get(currentId);

            //userPreference만큼 반복
            for(UserPreference userPreference: userPreferenceList){

                //현재 비교하는 userId와 userPreference의 userId가 같다면
                //preference값을 float으로 변환하여 userPreference객체에 저장
                if(currentId == userPreference.getUserId()){
                    Float percentPreference = userPreference.getPreference()/totalPreference ;
                    userPreference.setPreference((float) (percentPreference * 5.0));
                }
                //userPreference의 userId가 바뀌었다면
                //currentId를 바꾼다
                //총 preference의 값을 currentId와 맞게 바꾼다
                //preference값을 float으로 변환하여 userPreference객체에 저장
                else{
                    currentId = userPreference.getUserId();
                    totalPreference = totalPreferenceMap.get(currentId);
                    Float percentPreference = userPreference.getPreference()/totalPreference ;
                    userPreference.setPreference((float) (percentPreference * 5.0));
                }
            }

            //데이터베이스 객체 csv로 변환하여 매핑 후 저장
            for (UserPreference userPreference: userPreferenceList){
                csvWriter.write(userPreference, nameMapping);
            }

        }
        catch (Exception e){
           e.printStackTrace();
        }
        finally {
            csvWriter.close();
        }
    }

}
