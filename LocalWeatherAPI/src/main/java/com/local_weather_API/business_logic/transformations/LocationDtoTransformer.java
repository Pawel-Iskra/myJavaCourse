package com.local_weather_API.business_logic.transformations;

import com.local_weather_API.repository.Location;
import com.local_weather_API.presentation.LocationDto;
import com.local_weather_API.presentation.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoTransformer {

    public Location locationFromWeatherDtoToLocationEntity(WeatherDto weatherDto){
        Location location = new Location();
        location.setCountry(weatherDto.getCountryName());
        location.setCity(weatherDto.getCityName());
        location.setLatitude(weatherDto.getLatitude());
        location.setLongitude(weatherDto.getLongitude());
        return location;
    }

    public LocationDto locationFromEntityToDto(Location location){
        LocationDto locationDto = new LocationDto();
        locationDto.setCityName(location.getCity());
        locationDto.setCountryName(location.getCountry());
        locationDto.setLongitude(location.getLongitude());
        locationDto.setLatitude(location.getLatitude());
        return locationDto;
    }

}
