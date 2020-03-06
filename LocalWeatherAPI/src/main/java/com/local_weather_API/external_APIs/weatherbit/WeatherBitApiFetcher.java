package com.local_weather_API.external_APIs.weatherbit;

import com.local_weather_API.external_APIs.weatherbit.model.WeatherFromWeatherBit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherBitApiFetcher {

    @Value("${app.URL3}")
    private String url3;

    public WeatherFromWeatherBit getWeatherFromApi(float latitude, float longitude) {
        RestTemplate restTemplate = new RestTemplate();
        int latInt = (int) latitude;
        int latDec = (int) Math.abs((latitude * 100) - latInt * 100);
        int lonInt = (int) longitude;
        int lonDec = (int) Math.abs((longitude * 100) - lonInt * 100);
        return restTemplate.getForObject(String.format(url3, latInt, latDec, lonInt, lonDec), WeatherFromWeatherBit.class);
    }
}
