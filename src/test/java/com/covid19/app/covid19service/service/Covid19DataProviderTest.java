package com.covid19.app.covid19service.service;

import com.covid19.app.covid19service.dto.CountryData;
import com.covid19.app.covid19service.dto.CovidApiData;
import com.covid19.app.covid19service.dto.StateData;
import com.covid19.app.covid19service.dto.SummaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.internal.matchers.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

public class Covid19DataProviderTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Covid19DataProvider covid19DataProvider;

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("State Data Provider Test")
    void getStateDataTest(){

        Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(),ArgumentMatchers.any()))
                .thenReturn(getCovidApiData());
        StateData delhi = covid19DataProvider.getStatedData("Delhi");

        Assertions.assertAll(
                ()-> Assertions.assertEquals("Delhi",delhi.getLoc()),
                ()-> Assertions.assertEquals(587l,delhi.getDeaths()),
                ()-> Assertions.assertEquals(438756l,delhi.getDischarged()),
                ()-> Assertions.assertEquals(87643l,delhi.getConfirmedCasesForeign()),
                ()-> Assertions.assertEquals(565490l,delhi.getConfirmedCasesIndian()),
                ()-> Assertions.assertEquals(38746l,delhi.getTotalConfirmed())
        );
    }

    @Test
    @DisplayName("State Data Provider Test")
    void getStateDataTestNoData() {

        Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(getCovidApiData());
        StateData maharastra = covid19DataProvider.getStatedData("maharastra");

        Assertions.assertAll(
                () -> Assertions.assertEquals(null, maharastra.getLoc()),
                () -> Assertions.assertEquals(0, maharastra.getDeaths()),
                () -> Assertions.assertEquals(0, maharastra.getDischarged()),
                () -> Assertions.assertEquals(0, maharastra.getConfirmedCasesForeign()),
                () -> Assertions.assertEquals(0, maharastra.getConfirmedCasesIndian()),
                () -> Assertions.assertEquals(0, maharastra.getTotalConfirmed())
        );
    }
    private CovidApiData getCovidApiData() {
        CovidApiData covidApiData = new CovidApiData();
        CountryData countryData = new CountryData();
        SummaryData summaryData = new SummaryData();
        summaryData.setUpdateTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(10l);
        summaryData.setConfirmedCasesForeign(123123l);
        summaryData.setConfirmedCasesIndian(835438l);
        summaryData.setDeaths(34756l);
        summaryData.setDischarged(78456l);
        summaryData.setTotal(8887098l);
        countryData.setSummary(summaryData);
        StateData stateData = new StateData();

        stateData.setTotalConfirmed(38746l);
        stateData.setConfirmedCasesForeign(87643l);
        stateData.setConfirmedCasesIndian(565490l);
        stateData.setDeaths(587l);
        stateData.setDischarged(438756l);
        stateData.setLoc("Delhi");
        countryData.setRegional(new StateData[]{stateData});

        covidApiData.setData(countryData);
        covidApiData.setSuccess(true);
        covidApiData.setLastRefreshed(ZonedDateTime.now());
        covidApiData.setLastOriginUpdate(ZonedDateTime.now());
        return covidApiData;
    }


    private CovidApiData getCovidSummaryData() {
        CovidApiData covidApiData = new CovidApiData();
        CountryData countryData = new CountryData();
        SummaryData summaryData = new SummaryData();
        summaryData.setUpdateTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(10l);
        summaryData.setConfirmedCasesForeign(123123l);
        summaryData.setConfirmedCasesIndian(835438l);
        summaryData.setDeaths(34756l);
        summaryData.setDischarged(78456l);
        summaryData.setTotal(8887098l);
        countryData.setSummary(summaryData);
        covidApiData.setSuccess(true);
        covidApiData.setLastRefreshed(ZonedDateTime.now());
        covidApiData.setLastOriginUpdate(ZonedDateTime.now());

        return covidApiData;

    }
}
