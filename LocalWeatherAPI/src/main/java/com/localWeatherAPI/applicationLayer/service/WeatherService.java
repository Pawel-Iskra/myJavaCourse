package com.localWeatherAPI.applicationLayer.service;

import com.localWeatherAPI.applicationLayer.externalAPI.OpenWeatherAPIFetcher;
import com.localWeatherAPI.applicationLayer.transformations.LocationDtoTransformer;
import com.localWeatherAPI.dataLayer.Location;
import com.localWeatherAPI.dataLayer.LocationRepository;
import com.localWeatherAPI.dataLayer.Weather;
import com.localWeatherAPI.dataLayer.WeatherRepository;
import com.localWeatherAPI.presentationLayer.WeatherDto;
import com.localWeatherAPI.applicationLayer.transformations.WeatherDtoTransformer;
import com.localWeatherAPI.presentationLayer.WeatherDtoForCityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final OpenWeatherAPIFetcher openWeatherAPIFetcher;
    private final LocationRepository locationRepository;
    private final WeatherDtoTransformer weatherDtoTransformer;
    private final LocationDtoTransformer locationDtoTransformer;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, OpenWeatherAPIFetcher openWeatherAPIFetcher,
                          LocationRepository locationRepository, WeatherDtoTransformer weatherDtoTransformer,
                          LocationDtoTransformer locationDtoTransformer) {
        this.weatherRepository = weatherRepository;
        this.openWeatherAPIFetcher = openWeatherAPIFetcher;
        this.locationRepository = locationRepository;
        this.weatherDtoTransformer = weatherDtoTransformer;
        this.locationDtoTransformer = locationDtoTransformer;
    }

    public WeatherDto getCurrentWeatherForCityName(String city) {
        WeatherDto weatherDto = weatherDtoTransformer.weatherFromOpenWeatherApiToDto(
                openWeatherAPIFetcher.getWeatherFromAPI(city));
        addWeatherToDatabase(weatherDto);
        return weatherDto;
    }


    private void addWeatherToDatabase(WeatherDto weatherDto) {
        Weather weather = weatherDtoTransformer.weatherFromDtoToEntity(weatherDto);
        String cityName = weatherDto.getCityName();
        Optional location = locationRepository.findByCity(cityName);
        if (location.isPresent()) weather.setLocation((Location) location.get());
        else {
            Location locationToAdd = locationDtoTransformer
                    .locationFromWeatherDtoToLocationEntity(weatherDto);
            weather.setLocation(locationToAdd);
            locationRepository.save(locationToAdd);
        }
        weatherRepository.save(weather);
    }

    public List<WeatherDtoForCityList> getWeatherHistoryForCityName(String city) {
        Location location = locationRepository.findByCity(city)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No such location found in database.");
                });
        return weatherRepository.findByLocation(location)
                .stream()
                .map(weather -> weatherDtoTransformer.weatherFromEntityToWeatherDtoForCityList(weather))
                .collect(Collectors.toList());
    }

}
