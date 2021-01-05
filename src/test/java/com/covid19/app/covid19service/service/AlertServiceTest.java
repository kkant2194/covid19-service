package com.covid19.app.covid19service.service;

import com.covid19.app.covid19service.dto.AlertStatus;
import com.covid19.app.covid19service.dto.StateData;
import com.covid19.app.covid19service.dto.SummaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class AlertServiceTest {

    @InjectMocks
    private AlertService  alertService;

    @Mock
    private Covid19DataProvider covid19DataProvider;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("when the cases are below 1000 that is green Zone")
    void getAlertABoutStateTestGreen(){

        StateData stateData = new StateData();
        stateData.setTotalConfirmed(100l);
        Mockito.when(covid19DataProvider.getStatedData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getAlertAboutState("Arunachal Pradesh");

        Assertions.assertEquals("GREEN",status.getAlertLevel());
        Assertions.assertEquals(Arrays.asList("Everything is fine"),status.getMeasuresToBeTaken());
        Assertions.assertEquals(stateData,status.getSummaryData());

        Mockito.verify(covid19DataProvider,Mockito.times(1)).getStatedData("Arunachal Pradesh");
    }

    @Test
    @DisplayName("when the cases are above 1000 and less than 10000 that is Orange Zone")
    void getAlertABoutStateTestOrange(){

        StateData stateData = new StateData();
        stateData.setTotalConfirmed(1800l);
        Mockito.when(covid19DataProvider.getStatedData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getAlertAboutState("Arunachal Pradesh");

        Assertions.assertEquals("ORANGE",status.getAlertLevel());
        Assertions.assertEquals(Arrays.asList("Only essential services allowed",
                "List of essential services that cone under"),status.getMeasuresToBeTaken());
        Assertions.assertEquals(stateData,status.getSummaryData());
        Mockito.verify(covid19DataProvider,Mockito.times(1)).getStatedData("Arunachal Pradesh");
    }
    @Test
    @DisplayName("when the cases are above 1000 and less than 10000 that is Orange Zone")
    void getAlertABoutStateTestRED(){

        StateData stateData = new StateData();
        stateData.setTotalConfirmed(12000l);
        Mockito.when(covid19DataProvider.getStatedData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getAlertAboutState("Arunachal Pradesh");

        Assertions.assertEquals("RED",status.getAlertLevel());
        Assertions.assertEquals(Arrays.asList("Absolute Lock Down ",
                "Only food and medical services are allowed"),status.getMeasuresToBeTaken());
        Assertions.assertEquals(stateData,status.getSummaryData());
        Mockito.verify(covid19DataProvider,Mockito.times(1)).getStatedData("Arunachal Pradesh");
    }

    @Test
    @DisplayName("Overall summary of the data")
    void getOverAllSummary(){

        SummaryData summaryData = new SummaryData();
        summaryData.setUpdateTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(10l);
        summaryData.setConfirmedCasesForeign(123123l);
        summaryData.setConfirmedCasesIndian(835438l);
        summaryData.setDeaths(34756l);
        summaryData.setDischarged(78456l);
        summaryData.setTotal(8887098l);

        Mockito.when(covid19DataProvider.getSummaryData()).thenReturn(summaryData);

        SummaryData actualSummary = alertService.getOverAllSummary();

        Assertions.assertEquals(actualSummary,summaryData);
    }

    @Test
    void testHelloMethod(){
        String s = alertService.getHello("World");
        Assertions.assertEquals("Hello World",s);
    }

    @Test
    void testSumMethod(){
        int sum = alertService.sum(5,-1);
        Assertions.assertEquals(4,sum);
    }
}
