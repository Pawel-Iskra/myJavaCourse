package com.local_weather_API.business_logic.service;

import com.local_weather_API.external_APIs.darksky.DarkSkyApiFetcher;
import com.local_weather_API.external_APIs.openweathermap.OpenWeatherApiFetcher;
import com.local_weather_API.business_logic.transformations.LocationDtoTransformer;
import com.local_weather_API.external_APIs.openweathermap.model.WeatherFromOpenWeatherApi;
import com.local_weather_API.external_APIs.weatherbit.WeatherBitApiFetcher;
import com.local_weather_API.repository.Location;
import com.local_weather_API.repository.LocationRepository;
import com.local_weather_API.repository.Weather;
import com.local_weather_API.repository.WeatherRepository;
import com.local_weather_API.presentation.WeatherDto;
import com.local_weather_API.business_logic.transformations.WeatherDtoTransformer;
import com.local_weather_API.presentation.WeatherDtoForCityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final OpenWeatherApiFetcher openWeatherApiFetcher;
    private final DarkSkyApiFetcher darkSkyApiFetcher;
    private final WeatherBitApiFetcher weatherBitApiFetcher;
    private final WeatherDtoTransformer weatherDtoTransformer;
    private final LocationDtoTransformer locationDtoTransformer;


    @Autowired
    public WeatherService(WeatherRepository weatherRepository, OpenWeatherApiFetcher openWeatherAPIFetcher,
                          DarkSkyApiFetcher darkSkyApiFetcher, LocationRepository locationRepository,
                          WeatherDtoTransformer weatherDtoTransformer, LocationDtoTransformer locationDtoTransformer,
                          WeatherBitApiFetcher weatherBitApiFetcher) {
        this.weatherRepository = weatherRepository;
        this.openWeatherApiFetcher = openWeatherAPIFetcher;
        this.locationRepository = locationRepository;
        this.weatherDtoTransformer = weatherDtoTransformer;
        this.locationDtoTransformer = locationDtoTransformer;
        this.darkSkyApiFetcher = darkSkyApiFetcher;
        this.weatherBitApiFetcher = weatherBitApiFetcher;
    }

    public WeatherDto getCurrentWeatherForCityName(String city) {
        WeatherFromOpenWeatherApi weatherFromOpenWeatherApi = openWeatherApiFetcher.getWeatherFromAPI(city);
        Float longitude = weatherFromOpenWeatherApi.getCoord().getLongitude();
        Float latitude = weatherFromOpenWeatherApi.getCoord().getLatitude();
        WeatherDto weatherDto = weatherDtoTransformer.weatherFromApisToDto(
                weatherFromOpenWeatherApi,
                darkSkyApiFetcher.getWeatherFromApi(latitude, longitude),
                weatherBitApiFetcher.getWeatherFromApi(latitude,longitude));
        addWeatherToDatabase(weatherDto);
        return weatherDto;
    }


    private void addWeatherToDatabase(WeatherDto weatherDto) {
        Weather weather = weatherDtoTransformer.weatherFromDtoToEntity(weatherDto);
        String cityName = weatherDto.getCityName();
        Optional<Location> location = locationRepository.findByCity(cityName);
        location.ifPresentOrElse(
                location1 -> {
                    weather.setLocation(location1);
                },
                () -> {
                    Location locationToAdd = locationDtoTransformer
                            .locationFromWeatherDtoToLocationEntity(weatherDto);
                    locationRepository.save(locationToAdd);
                    weather.setLocation(locationToAdd);
                }
        );
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
