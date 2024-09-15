package com.weatherforecast.Controller;

import com.weatherforecast.Service.WeatherService;
import com.weatherforecast.util.WeatherUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherUtility.class);

    @Autowired
    private WeatherService weatherService;

    // http://localhost:8080/weather/{cityName}
    // http://localhost:8080/weather/Delhi
    @GetMapping("/{cityName}")
    public ResponseEntity<String> getTemperature(@PathVariable String cityName) {

        try {
            String temperature = weatherService.getTemperature(cityName);
            return ResponseEntity.ok("The temperature for city " + cityName + " is: " + temperature);

        } catch (Exception e) {
            logger.error("Error retrieving temperature: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving temperature: " + e.getMessage());
        }
    }



    // below one is according to --> City,, State Code   and Country code

    // http://localhost:8080/weather/{cityName}/{stateCode}/{countryCode}
    // http://localhost:8080/weather/Delhi/DL/IN

//    @GetMapping("/{cityName}/{stateCode}/{countryCode}")
//    public ResponseEntity<String> getTemperature(@PathVariable String cityName,
//                                                 @PathVariable String stateCode,
//                                                 @PathVariable String countryCode) {
//
//        try {
//            String temperature = weatherService.getTemperature(cityName, stateCode, countryCode);
//            return ResponseEntity.ok("The temperature for " + cityName + ", " + stateCode + ", " + countryCode + " is: " + temperature);
//        } catch (Exception e) {
//            logger.error("Error retrieving temperature: " + e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving temperature: " + e.getMessage());
//        }
//
//    }
}
