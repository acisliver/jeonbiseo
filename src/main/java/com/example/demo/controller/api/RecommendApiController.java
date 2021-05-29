package com.example.demo.controller.api;


import com.example.demo.dto.SearchTermDto;
import com.example.demo.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RecommendApiController {

    @Autowired
    private RecommendService recommendService;

  @PostMapping("api/recommend")
    public void recommend(@RequestBody SearchTermDto searchTermDto){
      int getItemId = recommendService.searchItemId(searchTermDto);
      if(getItemId > 0){
          List<String> recommendItemsName = recommendService.searchRecommendItem(getItemId);
          System.out.println("아이템"+recommendItemsName);
      }
  }

}
