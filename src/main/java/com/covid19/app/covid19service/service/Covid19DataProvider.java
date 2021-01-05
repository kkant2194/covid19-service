package com.covid19.app.covid19service.service;

import com.covid19.app.covid19service.dto.CovidApiData;
import com.covid19.app.covid19service.dto.StateData;
import com.covid19.app.covid19service.dto.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class Covid19DataProvider {

    @Autowired
    RestTemplate restTemplate;

    String uri = "https://api.rootnet.in/covid19-in/stats/latest";

    public  StateData getStatedData(String state){
       CovidApiData covidApiData =  restTemplate.getForObject(uri, CovidApiData.class);

        return Arrays.stream( covidApiData.getData().getRegional())
                .filter(e -> e.getLoc().equalsIgnoreCase(state))
                .findAny()
                .orElse(new StateData());

    }
    public SummaryData getSummaryData()
 {
         CovidApiData covidApiData =  restTemplate.getForObject(uri, CovidApiData.class);

         SummaryData  summaryData = covidApiData.getData().getSummary();

         summaryData.setUpdateTime(covidApiData.getLastRefreshed());

         return summaryData;

    }
}
