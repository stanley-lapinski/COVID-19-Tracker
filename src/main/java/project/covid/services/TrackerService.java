package project.covid.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.covid.models.LocationStats;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackerService {

    private static final String CASES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String DEATHS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static final String RECOVERIES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<LocationStats> allStatsCases = new ArrayList<>();
    private List<LocationStats> allStatsDeaths = new ArrayList<>();
    private List<LocationStats> allStatsRecoveries = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStatsCases = new ArrayList<>();
        List<LocationStats> newStatDeaths = new ArrayList<>();
        List<LocationStats> newStatRecoveries = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestCases = HttpRequest.newBuilder().uri(URI.create(CASES_DATA_URL)).build();
        HttpRequest requestDeaths = HttpRequest.newBuilder().uri(URI.create(DEATHS_DATA_URL)).build();
        HttpRequest requestRecoveries = HttpRequest.newBuilder().uri(URI.create(RECOVERIES_DATA_URL)).build();

        HttpResponse<String> responseCases = client.send(requestCases, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> responseDeaths = client.send(requestDeaths, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> responseRecoveries = client.send(requestRecoveries, HttpResponse.BodyHandlers.ofString());

        StringReader csvReaderCases = new StringReader(responseCases.body());
        StringReader csvReaderDeaths = new StringReader(responseDeaths.body());
        StringReader csvReaderRecoveries = new StringReader(responseRecoveries.body());

        Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReaderCases);
        Iterable<CSVRecord> recordsDeaths = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReaderDeaths);
        Iterable<CSVRecord> recordsRecoveries = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReaderRecoveries);

        for (CSVRecord record : recordsCases) {
            LocationStats statCases = new LocationStats();
            statCases.setState(record.get("Province/State"));
            statCases.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            statCases.setConfirmedCases(latestCases);
            statCases.setDifferenceCases(latestCases - prevDayCases);
            newStatsCases.add(statCases);
        }
        this.allStatsCases = newStatsCases;

        for (CSVRecord record : recordsDeaths) {
            LocationStats statDeaths = new LocationStats();
            statDeaths.setState(record.get("Province/State"));
            statDeaths.setCountry(record.get("Country/Region"));
            int latestDeaths = Integer.parseInt(record.get(record.size() - 1));
            int prevDayDeaths = Integer.parseInt(record.get(record.size() - 2));
            statDeaths.setConfirmedDeaths(latestDeaths);
            statDeaths.setDifferenceDeaths(latestDeaths - prevDayDeaths);
            newStatDeaths.add(statDeaths);
        }
        this.allStatsDeaths = newStatDeaths;

        for (CSVRecord record : recordsRecoveries) {
            LocationStats statsRecoveries = new LocationStats();
            statsRecoveries.setState(record.get("Province/State"));
            statsRecoveries.setCountry(record.get("Country/Region"));
            int latestRecoveries = Integer.parseInt(record.get(record.size() - 1));
            int prevDayRecoveries = Integer.parseInt(record.get(record.size() - 2));
            statsRecoveries.setConfirmedRecoveries(latestRecoveries);
            statsRecoveries.setDifferenceRecoveries(latestRecoveries - prevDayRecoveries);
            newStatRecoveries.add(statsRecoveries);
        }
        this.allStatsRecoveries = newStatRecoveries;
    }

    public List<LocationStats> getAllStatsCases() {
        return allStatsCases;
    }

    public List<LocationStats> getAllStatsDeaths() {
        return allStatsDeaths;
    }

    public List<LocationStats> getAllStatsRecoveries() {
        return allStatsRecoveries;
    }
}
