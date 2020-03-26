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

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = service.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestConfirmedCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
