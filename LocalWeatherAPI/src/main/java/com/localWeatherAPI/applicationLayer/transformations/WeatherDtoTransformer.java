package com.localWeatherAPI.applicationLayer.transformations;

import com.localWeatherAPI.applicationLayer.externalAPI.WeatherFromOpenWeatherAPI;
import com.localWeatherAPI.dataLayer.Location;
import com.localWeatherAPI.dataLayer.Weather;
import com.localWeatherAPI.presentationLayer.WeatherDto;
import com.localWeatherAPI.presentationLayer.WeatherDtoForCityList;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherDtoTransformer {

    public WeatherDto weatherFromOpenWeatherApiToDto(WeatherFromOpenWeatherAPI weatherFromOpenWeatherApi) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCityName(weatherFromOpenWeatherApi.getCityName());
        weatherDto.setCountryName(weatherFromOpenWeatherApi.getSys().getCountry());
        weatherDto.setLongitude(weatherFromOpenWeatherApi.getCoord().getLongitude());
        weatherDto.setLatitude(weatherFromOpenWeatherApi.getCoord().getLatitude());
        weatherDto.setMainWeatherType(weatherFromOpenWeatherApi.getWeather()[0].getMainWeatherType());
        weatherDto.setWeatherDescription(weatherFromOpenWeatherApi.getWeather()[0].getDescription());
        weatherDto.setTemperature(weatherFromOpenWeatherApi.getMain().getTemperature());
        weatherDto.setTemperatureSensed(weatherFromOpenWeatherApi.getMain().getTemperatureFeelsLike());
        weatherDto.setPressure(weatherFromOpenWeatherApi.getMain().getPressure());
        weatherDto.setHumidity(weatherFromOpenWeatherApi.getMain().getHumidity());
        weatherDto.setWindSpeed(weatherFromOpenWeatherApi.getWind().getSpeed());
        weatherDto.setWindDegrees(weatherFromOpenWeatherApi.getWind().getDegrees());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        weatherDto.setDateTime(dtf.format(now).toString());
        return weatherDto;
    }

    public Weather weatherFromDtoToEntity(WeatherDto weatherDto){
        Weather weather = new Weather();
        weather.setMainWeatherType(weatherDto.getMainWeatherType());
        weather.setDescription(weatherDto.getWeatherDescription());
        weather.setTemperature(weatherDto.getTemperature());
        weather.setTemperatureFeelsLike(weatherDto.getTemperatureSensed());
        weather.setPressure(weatherDto.getPressure());
        weather.setHumidity(weatherDto.getHumidity());
        weather.setWindSpeed(weatherDto.getWindSpeed());
        weather.setWindDegrees(weatherDto.getWindDegrees());
        weather.setDateTime(weatherDto.getDateTime());
        weather.setLocation(new Location());
        return weather;
    }

    public WeatherDto weatherFromEntityToDto(Weather weather){
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCityName(weather.getLocation().getCity());
        weatherDto.setCountryName(weather.getLocation().getCountry());
        weatherDto.setLongitude(weather.getLocation().getLongitude());
        weatherDto.setLatitude(weather.getLocation().getLatitude());
        weatherDto.setMainWeatherType(weather.getMainWeatherType());
        weatherDto.setWeatherDescription(weather.getDescription());
        weatherDto.setTemperature(weather.getTemperature());
        weatherDto.setTemperatureSensed(weather.getTemperatureFeelsLike());
        weatherDto.setPressure(weather.getPressure());
        weatherDto.setHumidity(weather.getHumidity());
        weatherDto.setWindSpeed(weather.getWindSpeed());
        weatherDto.setWindDegrees(weather.getWindDegrees());
        weatherDto.setDateTime(weather.getDateTime());
        return weatherDto;
    }

    public WeatherDtoForCityList weatherFromEntityToWeatherDtoForCityList(Weather weather){
        WeatherDtoForCityList weatherDtoForCityList = new WeatherDtoForCityList();
        weatherDtoForCityList.setMainWeatherType(weather.getMainWeatherType());
        weatherDtoForCityList.setWeatherDescription(weather.getDescription());
        weatherDtoForCityList.setTemperature(weather.getTemperature());
        weatherDtoForCityList.setTemperatureSensed(weather.getTemperatureFeelsLike());
        weatherDtoForCityList.setPressure(weather.getPressure());
        weatherDtoForCityList.setHumidity(weather.getHumidity());
        weatherDtoForCityList.setWindSpeed(weather.getWindSpeed());
        weatherDtoForCityList.setWindDegrees(weather.getWindDegrees());
        weatherDtoForCityList.setDateTime(weather.getDateTime());
        return weatherDtoForCityList;
    }

}
