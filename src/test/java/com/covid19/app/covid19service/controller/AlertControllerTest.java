package com.covid19.app.covid19service.controller;


import com.covid19.app.covid19service.dto.AlertStatus;
import com.covid19.app.covid19service.dto.SummaryData;
import com.covid19.app.covid19service.service.AlertService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertService alertService;

    @Test
    void getAlertAboutState() throws Exception {

        AlertStatus alertStatus = new AlertStatus();
        alertStatus.setAlertLevel("GREEN");

        Mockito.when(alertService.getAlertAboutState(ArgumentMatchers.anyString())).thenReturn(alertStatus);

        mockMvc.perform(MockMvcRequestBuilders.get("/india/delhi"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{\"alertLevel\":\"GREEN\",\"measuresToBeTaken\":null,\"summaryData\":null}"));

    }

    @Test
    void getSummaryData() throws Exception {

        SummaryData  summaryData = new SummaryData();

        Mockito.when(alertService.getOverAllSummary()).thenReturn(summaryData);

        mockMvc.perform(MockMvcRequestBuilders.get("/india/summary"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("" +
                        "{\"total\":null,\"confirmedCasesIndian\":null,\"confirmedCasesForeign\":null,\"discharged\":null,\"deaths\":null,\"confirmedButLocationUnidentified\":null,\"updateTime\":null}"));

    }

    @Test
    void invalidEndPoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/india123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
