package com.covid19.app.covid19service.controller;


import com.covid19.app.covid19service.dto.AlertStatus;
import com.covid19.app.covid19service.dto.SummaryData;
import com.covid19.app.covid19service.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/india")
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping("/{state}")
    AlertStatus getAlertAboutState(@PathVariable String state){
        return alertService.getAlertAboutState(state);

    }

    @GetMapping("/summary")
    SummaryData overAllSummary(){
        return alertService.getOverAllSummary();
    }

}
