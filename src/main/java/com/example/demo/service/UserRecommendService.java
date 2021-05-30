package com.example.demo.service;

import com.example.demo.dto.RequestUserPreferenceDto;
import com.example.demo.model.Application;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.UserPreferenceRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
        int plusPreference = 1;
        if (fitItems != null) {
            for (int i = 0; i < fitItems.size(); i++) {
                userPreferenceRepository.increacePrefernce(plusPreference, fitItems.get(i).getId(), userId);
            }
        }
    }

    @Transactional
    public List<RecommendedItem> userRecommender() throws IOException, TasteException {
        DataModel model = new FileDataModel(new File("src/main/data/userpreference.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(1, 1);
        if(recommendations.size()==0) {
            System.out.println("***************************");
            System.out.printf("Nothing");
            System.out.println("\n***************************");
            return null;
        }
        for (RecommendedItem recommendedItem : recommendations) {
            System.out.println("***************************");
            System.out.printf(String.valueOf(recommendedItem));
            System.out.println("\n***************************");
        }
        return recommendations;
    }

    @Transactional
    public void dataToCSV() throws IOException {
        ICsvBeanWriter csvWriter = null;
        try {
            List<UserPreference> userPreferenceList = this.listAll();

            csvWriter =
                    new CsvBeanWriter(new OutputStreamWriter(new FileOutputStream("src/main/data/userpreference.csv")), CsvPreference.STANDARD_PREFERENCE);

            String[] nameMapping = {"userId", "applicationId", "preference"};

            //사용자 개인의 preference 총합 계산
            //현재 userId
            //총 preference를 구하기 위한 초기값
            //userId와 totalPreference를 매핑해줄 Map
            Integer currentId = userPreferenceList.get(0).getUserId();
            Integer totalPreference = 0;
            HashMap<Integer, Integer> totalPreferenceMap = new HashMap<>();

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

            //---------------------------------------------------

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
                    userPreference.setPreference(5 * userPreference.getPreference()/totalPreference);
                }
                //userPreference의 userId가 바뀌었다면
                //currentId를 바꾼다
                //총 preference의 값을 currentId와 맞게 바꾼다
                //preference값을 float으로 변환하여 userPreference객체에 저장
                else{
                    currentId = userPreference.getUserId();
                    totalPreference = totalPreferenceMap.get(currentId);
                    userPreference.setPreference(5 * userPreference.getPreference()/totalPreference);
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
