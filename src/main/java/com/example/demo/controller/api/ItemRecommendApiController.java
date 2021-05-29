package com.example.demo.controller.api;


import com.example.demo.dto.SearchTermDto;
import com.example.demo.service.ItemRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemRecommendApiController {

    @Autowired
    private ItemRecommendService itemRecommendService;

  @PostMapping("api/item-recommend")
    public void recommend(@RequestBody SearchTermDto searchTermDto){
      int getItemId = itemRecommendService.searchItemId(searchTermDto);
      if(getItemId > 0){
          List<String> recommendItemsName = itemRecommendService.searchRecommendItem(getItemId);
          System.out.println("아이템"+recommendItemsName);
      }
  }

}
