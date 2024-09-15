package com.weatherforecast.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component

public class WeatherUtility {

    private static final Logger logger = LoggerFactory.getLogger(WeatherUtility.class);

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public String fetchTemperature(String cityName) throws Exception {

        //   just below one is according to --> City,, State Code   and Country code

//public String fetchTemperature(String cityName, String stateCode, String countryCode) throws Exception {

        // Building the full URL with           city name and API key
        String fullUrl = apiUrl + "?q=" + cityName + "&appid=" + apiKey + "&units=metric";  // metric for Celsius

        //   just below one is according to --> City,, State Code   and Country code

        //  Building the full URL with city name, state code, country code, and API key
//               String fullUrl = apiUrl + "?q=" + cityName + "," + stateCode + "," + countryCode + "&appid=" + apiKey + "&units=metric";  // metric for Celsius

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);
            logger.info("API Response: " + response.getBody());

            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject json = new JSONObject(response.getBody());
                double tempInCelsius = json.getJSONObject("main").getDouble("temp");
                return String.valueOf(tempInCelsius) + " °C";
            } else {
                throw new Exception("Failed to retrieve temperature. Status code: " + response.getStatusCode() + ". Response body: " + response.getBody());
            }
        } catch (Exception e) {
            logger.error("Error fetching temperature: " + e.getMessage(), e);
            throw e;
        }
    }


}