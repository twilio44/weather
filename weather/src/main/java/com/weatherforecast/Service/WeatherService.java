package com.weatherforecast.Service;

public interface WeatherService {
    String getTemperature(String cityName) throws Exception;

//     below one is according to --> City,, State Code   and Country code
//     String getTemperature(String cityName, String stateCode, String countryCode) throws Exception;
}
