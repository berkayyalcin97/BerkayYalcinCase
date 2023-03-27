package com.example.berkayyalcincase.controller;

import com.example.berkayyalcincase.model.Earthquake;
import com.example.berkayyalcincase.exception.EarthquakeApiRequestException;
import com.example.berkayyalcincase.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/earthquakes")
public class EarthquakeController {
    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/{country}/{days}")
    public List<Earthquake> getEarthquakes(@PathVariable String country, @PathVariable int days) throws EarthquakeApiRequestException {

        return earthquakeService.getEarthquakes(country, days);
    }
}