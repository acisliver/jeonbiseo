package com.example.demo.controller.api;

import com.example.demo.dto.SearchTermDto;
import com.example.demo.model.Application;
import com.example.demo.model.ESApplication;
import com.example.demo.service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CompareController {

    @Autowired
    CompareService compareService;

//    @PostMapping("/api/compare/save")
//    public void compareSave(){
//        compareService.saveCompare();;
//    }

    @GetMapping("/api/compare/view")
    public List<Application> viewCompare(){
        return compareService.getAll();
    }

    @PostMapping("/api/compare/search")
    public List<ESApplication> search(@RequestBody SearchTermDto searchTermDto){
        List<ESApplication> applications=compareService.search(searchTermDto.getSearchTerm());
        return applications;
    }

    @GetMapping("/api/compare/select")
    public Optional<Application> selectApplication(@RequestBody SearchTermDto searchTermDto){
        return compareService.findApplication(searchTermDto.getSearchTerm());
    }
}
