package com.weatherforecast.Service.impl;

import com.weatherforecast.Service.WeatherService;
import com.weatherforecast.util.WeatherUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherUtility.class);

    @Autowired
    private WeatherUtility weatherUtility;

    @Override
    public String getTemperature(String cityName) throws Exception {
        try {
            return weatherUtility.fetchTemperature(cityName);
        } catch (Exception e) {
            logger.error("Error in WeatherService: " + e.getMessage(), e);
            throw e;
        }
    }

    //        below one is according to --> City,, State Code   and Country code

//    @Override
//    public String getTemperature(String cityName, String stateCode, String countryCode) throws Exception {
//        try {
//            return weatherUtility.fetchTemperature(cityName, stateCode, countryCode);
//        } catch (Exception e) {
//            logger.error("Error in WeatherService: " + e.getMessage(), e);
//            throw e;
//        }
//    }
}
