package com.local_weather_API.presentation;

import com.local_weather_API.business_logic.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getAllLocationsFromDatabase(){
        return locationService.getAllLocationsFromDatabase();
    }

    @GetMapping("/{country}")
    public List<LocationDto> getLocationsForCountry(@PathVariable String country){
        return locationService.getLocationsForCountry(country);
    }

    @DeleteMapping("/{city}")
    public void deleteLocationWithAllItsWeathers(@PathVariable String city){
        locationService.removeLocationFromDatabaseWithAllItsWeathers(city);
    }



}
