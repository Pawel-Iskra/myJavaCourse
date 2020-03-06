package com.local_weather_API.external_APIs.darksky;

import com.local_weather_API.external_APIs.darksky.model.Currently;
import com.local_weather_API.external_APIs.darksky.model.WeatherFromDarkSkyApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DarkSkyApiFetcher {

    @Value("${app.URL2}")
    private String url2;

    public WeatherFromDarkSkyApi getWeatherFromApi(float latitude, float longitude) {
        RestTemplate restTemplate = new RestTemplate();
        int latInt = (int) latitude;
        int latDec = (int) Math.abs((latitude * 100) - latInt * 100);
        int lonInt = (int) longitude;
        int lonDec = (int) Math.abs((longitude * 100) - lonInt * 100);
        return restTemplate.getForObject(String.format(url2,latInt, latDec, lonInt, lonDec), WeatherFromDarkSkyApi.class);
    }


}
