package com.localWeatherAPI.applicationLayer.externalAPI;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherAPIFetcher {

    private static final String URL =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=c9dcfb27784dc807b7f2c7e895ea7654&units=metric";


    public WeatherFromOpenWeatherAPI getWeatherFromAPI(String city) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherFromOpenWeatherAPI fromOpenWeatherAPI =
                restTemplate.getForObject(String.format(URL, city), WeatherFromOpenWeatherAPI.class);
        return fromOpenWeatherAPI;
    }
//
//    public LocationFromOpenWeatherAPI getLocationFromAPI(String city){
//        RestTemplate restTemplate = new RestTemplate();
//        LocationFromOpenWeatherAPI locationFromOpenWeatherAPI =
//                restTemplate.getForObject(String.format(URL, city),LocationFromOpenWeatherAPI.class);
//        return locationFromOpenWeatherAPI;
//    }
    
}
