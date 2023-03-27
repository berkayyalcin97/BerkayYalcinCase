package com.example.berkayyalcincase.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.berkayyalcincase.model.Earthquake;
import com.example.berkayyalcincase.exception.EarthquakeApiRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class EarthquakeService {
    private final String API_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    public List<Earthquake> getEarthquakes(String country, int days) throws EarthquakeApiRequestException {
        RestTemplate restTemplate = new RestTemplate();
        String startDate = LocalDate.now().minusDays(days).toString();
        String endDate = LocalDate.now().toString();
        String url = API_URL + "?format=geojson&starttime=" + startDate + "&endtime=" + endDate;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Earthquake> earthquakes = new ArrayList<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode featuresNode = rootNode.path("features");

            for (JsonNode featureNode : featuresNode) {
                JsonNode propertiesNode = featureNode.path("properties");
                String earthquakeCountry = "";

                earthquakeCountry = propertiesNode.path("place").asText();

                if (earthquakeCountry.contains(country)) {
                    String place = propertiesNode.path("place").asText().split(", ")[0];
                    double magnitude = propertiesNode.path("mag").asDouble();
                    String date = dateFormatter.format(propertiesNode.path("time").asLong());
                    String time = timeFormatter.format(propertiesNode.path("time").asLong());
                    Earthquake earthquake = new Earthquake(country, place, magnitude, date, time);
                    earthquakes.add(earthquake);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        if (earthquakes.isEmpty()) {
            System.out.println("No Earthquakes were recorded in " + country + " past " + days + " days");
            throw new EarthquakeApiRequestException("No Earthquakes were recorded in " + country + " past " + days + " days");
        }
        //I made a Simple for loop to show data in also console
        System.out.println("Eartquakes in : "+country+"past "+days+"are down below listet : ");
        for(Earthquake earthquake : earthquakes){

            System.out.println("Country : "+earthquake.getCountry());
            System.out.println("Place : "+earthquake.getPlace());
            System.out.println("Magnitute : "+earthquake.getMagnitude());
            System.out.println("Date : "+earthquake.getDate());
            System.out.println("Time : "+earthquake.getTime());
            System.out.println("**************************************");
        }
        return earthquakes;
    }
}