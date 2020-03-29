package project.covid.models;

public class LocationStats {

    private String state;
    private String country;
    private int confirmedCases;
    private int differenceCases;
    private int confirmedDeaths;
    private int differenceDeaths;
    private int confirmedRecoveries;
    private int differenceRecoveries;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public int getDifferenceCases() {
        return differenceCases;
    }

    public void setDifferenceCases(int differenceCases) {
        this.differenceCases = differenceCases;
    }

    public int getConfirmedDeaths() {
        return confirmedDeaths;
    }

    public void setConfirmedDeaths(int confirmedDeaths) {
        this.confirmedDeaths = confirmedDeaths;
    }

    public int getDifferenceDeaths() {
        return differenceDeaths;
    }

    public void setDifferenceDeaths(int differenceDeaths) {
        this.differenceDeaths = differenceDeaths;
    }

    public int getConfirmedRecoveries() {
        return confirmedRecoveries;
    }

    public void setConfirmedRecoveries(int confirmedRecoveries) {
        this.confirmedRecoveries = confirmedRecoveries;
    }

    public int getDifferenceRecoveries() {
        return differenceRecoveries;
    }

    public void setDifferenceRecoveries(int differenceRecoveries) {
        this.differenceRecoveries = differenceRecoveries;
    }
}
