package com.example.demo.service;

import com.example.demo.dto.RequestUserPreferenceDto;
import com.example.demo.model.Application;
import com.example.demo.model.User;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.CompareRepository;
import com.example.demo.repository.UserPreferenceRepository;
import lombok.Getter;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

@Service
public class UserRecommendService {

    @Getter
    @PersistenceContext
    private EntityManager em;


    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    public List<UserPreference> listAll() {
        return userPreferenceRepository.findAll(Sort.by("userId").ascending());
    }

    @Transactional
    public void getPreferenceItem(RequestUserPreferenceDto requestUserPreferenceDto, int userId) {
        System.out.println("dto는" + requestUserPreferenceDto);

        ArrayList<Application> allSeletApllicateId=new ArrayList();

        allSeletApllicateId.addAll(compareRepository.findByOs(requestUserPreferenceDto.getOs()));
        allSeletApllicateId.addAll(compareRepository.findByAppWeight(requestUserPreferenceDto.getAppWeight()));
        allSeletApllicateId.addAll(compareRepository.findByAppSize(requestUserPreferenceDto.getAppSize()));
        allSeletApllicateId.addAll(compareRepository.findByusePen(requestUserPreferenceDto.getUsePen()));
        allSeletApllicateId.addAll(compareRepository.findByNetwork(requestUserPreferenceDto.getNetwork()));

        Collections.sort(allSeletApllicateId,new UserComparator());
        System.out.println("정렬 확인"+allSeletApllicateId);
//        List<Application> fitItems = compareRepository.fitCriteriaItems(requestUserPreferenceDto.getOs(),
//                requestUserPreferenceDto.getAppWeight(), requestUserPreferenceDto.getAppSize(),
//                requestUserPreferenceDto.getUsePen(), requestUserPreferenceDto.getNetwork());

        //System.out.println("맞는 기종" + fitItems);

        //사용자 취향에 맞는 전자기기가 있을 때 application의 개수만큼 더함
        int plusPreference = 20;
        if (allSeletApllicateId != null) {
            int count=0;
            int beforeId=allSeletApllicateId.get(0).getId();
            for (int i = 0; i < allSeletApllicateId.size(); i++) {
                if(beforeId!=allSeletApllicateId.get(i).getId()){
                    beforeId=allSeletApllicateId.get(i).getId();
                    count=0;
                }
                else{
                    count+=1;
                }
                if(count>2) {
                    if(userPreferenceRepository.findByUserIdAndApplicationId(userId,beforeId)==null){
                        UserPreference userPreference=new UserPreference();
                        userPreference.setUserId(userId);
                        userPreference.setApplicationId(beforeId);
                        userPreference.setPreference(plusPreference);
                        userPreferenceRepository.save(userPreference);
                    }
                    else{
                        userPreferenceRepository.increacePrefernce(plusPreference, allSeletApllicateId.get(i).getId(), userId);
                    }
                }
            }
        }
    }

    @Transactional
    public Optional<Application> userRecommender(User user) throws IOException, TasteException {
        DataModel model = new FileDataModel(new File("src/main/data/userpreference.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(user.getId(), 1);
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
        return compareRepository.findById(Integer.parseInt(String.valueOf(recommendations.get(0).getItemID())));
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

                em.detach(userPreference);
                //현재 비교하는 userId와 userPreference의 userId가 같다면
                //선호도의 비율을 10% 단위로 나누어 1~5까지 매핑
                if(currentId == userPreference.getUserId()){
                    int percent = 10 * userPreference.getPreference()/totalPreference;
                    switch (percent){
                        case 0:
                            userPreference.setPreference(1);
                            break;
                        case 1:
                            userPreference.setPreference(2);
                            break;
                        case 2:
                            userPreference.setPreference(3);
                            break;
                        case 3:
                            userPreference.setPreference(4);
                            break;
                        default:
                            userPreference.setPreference(5);
                            break;
                    }
                }
                //userPreference의 userId가 바뀌었다면
                //currentId를 바꾼다
                //총 preference의 값을 currentId와 맞게 바꾼다
                //preference값을 float으로 변환하여 userPreference객체에 저장
                else{
                    currentId = userPreference.getUserId();
                    totalPreference = totalPreferenceMap.get(currentId);
                    int percent = 10 * userPreference.getPreference()/totalPreference;
                    switch (percent){
                        case 0:
                            userPreference.setPreference(1);
                            break;
                        case 1:
                            userPreference.setPreference(2);
                            break;
                        case 2:
                            userPreference.setPreference(3);
                            break;
                        case 3:
                            userPreference.setPreference(4);
                            break;
                        default:
                            userPreference.setPreference(5);
                            break;
                    }
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

class UserComparator implements Comparator<Application>{


    @Override
    public int compare(Application o1, Application o2) {
        if(o1.getId()>o2.getId()) return 1;
        if(o1.getId()<o2.getId()) return -1;
        return 0;
    }
}
