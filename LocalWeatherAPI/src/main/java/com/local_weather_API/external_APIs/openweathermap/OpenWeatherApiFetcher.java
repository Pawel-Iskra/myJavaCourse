package com.local_weather_API.external_APIs.openweathermap;

import com.local_weather_API.external_APIs.openweathermap.model.WeatherFromOpenWeatherApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherApiFetcher {

    @Value("${app.URL1}")
    private String url1;

    public WeatherFromOpenWeatherApi getWeatherFromAPI(String city) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(url1, city), WeatherFromOpenWeatherApi.class);
    }

}
