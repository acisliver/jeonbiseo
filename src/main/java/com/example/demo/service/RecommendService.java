package com.example.demo.service;

import com.example.demo.dto.SearchTermDto;
import com.example.demo.model.Application;
import com.example.demo.repository.RecommendRepository;
import com.example.demo.service.CompareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private RecommendRepository recommendRepository;

    public int searchItemId(SearchTermDto searchTermDto) {
        return compareRepository.searchId(searchTermDto.getSearchTerm());
    }

    public List<String> searchRecommendItem(int getItemId) {
        List<Integer> recommendItemIds= recommendRepository.searchRecommend(getItemId);
        List<String> recommendItem = new ArrayList<String>();
        for(int i = 0; i< recommendItemIds.size(); i++){
            String application = compareRepository.searchApplication(recommendItemIds.get(i));
            recommendItem.add(application);
        }
        return recommendItem;
    }
}
