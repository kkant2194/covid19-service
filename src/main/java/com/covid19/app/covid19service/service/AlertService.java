package com.covid19.app.covid19service.service;

import com.covid19.app.covid19service.dto.AlertStatus;
import com.covid19.app.covid19service.dto.StateData;
import com.covid19.app.covid19service.dto.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AlertService {


    @Autowired
    private Covid19DataProvider covid19DataProvider;


    public AlertStatus getAlertAboutState(String state) {

        AlertStatus  alertStatus = new AlertStatus();

        StateData stateData = covid19DataProvider.getStatedData(state);

        alertStatus.setSummaryData(stateData);

        if (stateData.getTotalConfirmed()< 1000){
            alertStatus.setAlertLevel("GREEN");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Everything is fine"));

        }
        else if(stateData.getTotalConfirmed() >=1000 && stateData.getTotalConfirmed() <10000){
            alertStatus.setAlertLevel("ORANGE");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Only essential services allowed",
                    "List of essential services that cone under"));

        }
        else{
            alertStatus.setAlertLevel("RED");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Absolute Lock Down ",
                    "Only food and medical services are allowed"));
        }
        return alertStatus;
    }

    public SummaryData getOverAllSummary() {
       return covid19DataProvider.getSummaryData();

    }

    //String s  = "World";
    public String getHello(String s){
        return "Hello " + s;
    }

    public int sum(int a , int b){
        return a+b;
    }
}
