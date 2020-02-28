package com.localWeatherAPI.applicationLayer.transformations;

import com.localWeatherAPI.dataLayer.Location;
import com.localWeatherAPI.presentationLayer.LocationDto;
import com.localWeatherAPI.presentationLayer.WeatherDto;
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
