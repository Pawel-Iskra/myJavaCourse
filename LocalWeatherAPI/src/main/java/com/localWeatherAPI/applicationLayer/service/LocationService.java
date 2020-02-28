package com.localWeatherAPI.applicationLayer.service;

import com.localWeatherAPI.applicationLayer.transformations.LocationDtoTransformer;
import com.localWeatherAPI.dataLayer.Location;
import com.localWeatherAPI.dataLayer.LocationRepository;
import com.localWeatherAPI.dataLayer.WeatherRepository;
import com.localWeatherAPI.presentationLayer.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationDtoTransformer locationDtoTransformer;
    private final WeatherRepository weatherRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationDtoTransformer locationDtoTransformer,
                           WeatherRepository weatherRepository) {
        this.locationRepository = locationRepository;
        this.locationDtoTransformer = locationDtoTransformer;
        this.weatherRepository = weatherRepository;
    }

    public List<LocationDto> getAllLocationsFromDatabase() {
        return locationRepository.findAllByOrderByCountryAsc()
                .stream()
                .map(location -> locationDtoTransformer.locationFromEntityToDto(location))
                .collect(Collectors.toList());
    }

    public List<LocationDto> getLocationsForCountry(String country) {
        return locationRepository.findByCountryOrderByCityAsc(country)
                .stream()
                .map(location -> locationDtoTransformer.locationFromEntityToDto(location))
                .collect(Collectors.toList());
    }

    public void removeLocationFromDatabaseWithAllItsWeathers(String city){
        Location locationToDelete = locationRepository.findByCity(city)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No such location found in database.");
                });
        weatherRepository.findByLocation(locationToDelete)
                .stream()
                .forEach(weather -> weatherRepository.delete(weather));
        locationRepository.delete(locationToDelete);
    }

}
