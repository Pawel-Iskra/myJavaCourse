package com.local_weather_API.presentation;

import com.local_weather_API.business_logic.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherDto getCurrentWeatherForCityName(@RequestParam String city) {
        return weatherService.getCurrentWeatherForCityName(city);
    }

    @GetMapping("/history/{city}")
    public List<WeatherDtoForCityList> getWeatherHistoryForCityName(@PathVariable String city) {
        return weatherService.getWeatherHistoryForCityName(city);
    }

}

