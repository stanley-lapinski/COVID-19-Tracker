package project.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.covid.models.LocationStats;
import project.covid.services.Covid19Service;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private Covid19Service service;

    @GetMapping("/cases")
    public String home(Model model) {
        List<LocationStats> allStats = service.getAllStatsCases();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getConfirmedCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDifferenceCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }

    @GetMapping("/deaths")
    public String deaths(Model model) {
        List<LocationStats> allStats = service.getAllStatsDeaths();
        int totalDeaths = allStats.stream().mapToInt(stat -> stat.getConfirmedDeaths()).sum();
        int totalNewDeaths = allStats.stream().mapToInt(stat -> stat.getDifferenceDeaths()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalDeaths", totalDeaths);
        model.addAttribute("totalNewDeaths", totalNewDeaths);
        return "deaths";
    }

    @GetMapping("/recoveries")
    public String recoveries(Model model) {
        List<LocationStats> allStats = service.getAllStatsRecoveries();
        int totalRecoveries = allStats.stream().mapToInt(stat -> stat.getConfirmedRecoveries()).sum();
        int totalNewRecoveries = allStats.stream().mapToInt(stat -> stat.getDifferenceRecoveries()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalRecoveries", totalRecoveries);
        model.addAttribute("totalNewRecoveries", totalNewRecoveries);
        return "recoveries";
    }
}
