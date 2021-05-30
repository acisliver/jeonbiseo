package com.example.demo.controller.api;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.RequestUserPreferenceDto;

import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("api/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Dispostition";
        String headervalue = "attachment; filename=users_" + currentDateTime + ".csv";

        response.setHeader(headerKey, headervalue);

        List<UserPreference> userPreferenceList = userRecommendService.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "User ID", "Application ID", "Preference"};
        String[] nameMapping = {"id", "userId", "applicationId", "preference"};

        csvWriter.writeHeader(csvHeader);

        for (UserPreference userPreference: userPreferenceList){
            csvWriter.write(userPreference, nameMapping);
        }

        csvWriter.close();
    }

}
