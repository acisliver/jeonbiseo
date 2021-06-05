package com.example.demo.controller.api;

import com.example.demo.dto.SearchTermDto;
import com.example.demo.model.Application;
import com.example.demo.model.ESApplication;
import com.example.demo.service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/compare/search/{searchWord}")
    public List<ESApplication> search(@RequestParam("searchWord") String searchWord){
        List<ESApplication> applications=compareService.search(searchWord);
        return applications;
    }

    @GetMapping("/api/compare/select")
    public Optional<Application> selectApplication(@RequestParam("sqlid") String sqlId,@RequestParam(value="id",required = false) Integer id){
        return compareService.findApplication(sqlId,id);
    }
}
