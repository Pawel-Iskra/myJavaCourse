package com.local_weather_API.business_logic.transformations;

import com.local_weather_API.external_APIs.darksky.model.WeatherFromDarkSkyApi;
import com.local_weather_API.external_APIs.openweathermap.model.WeatherFromOpenWeatherApi;
import com.local_weather_API.external_APIs.weatherbit.model.WeatherFromWeatherBit;
import com.local_weather_API.repository.Location;
import com.local_weather_API.repository.Weather;
import com.local_weather_API.presentation.WeatherDto;
import com.local_weather_API.presentation.WeatherDtoForCityList;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherDtoTransformer {

    public WeatherDto weatherFromApisToDto(WeatherFromOpenWeatherApi weatherFromOpenWeatherApi,
                                           WeatherFromDarkSkyApi weatherFromDarkSkyApi,
                                           WeatherFromWeatherBit weatherFromWeatherBit) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCityName(weatherFromOpenWeatherApi.getCityName());
        weatherDto.setCountryName(weatherFromOpenWeatherApi.getSys().getCountry());
        weatherDto.setLongitude(weatherFromOpenWeatherApi.getCoord().getLongitude());
        weatherDto.setLatitude(weatherFromOpenWeatherApi.getCoord().getLatitude());
        weatherDto.setMainWeatherType(weatherFromOpenWeatherApi.getWeather()[0].getMainWeatherType());
        weatherDto.setWeatherDescription(weatherFromOpenWeatherApi.getWeather()[0].getDescription());
        weatherDto.setTemperature(calculateAverageTemperature(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        weatherDto.setTemperatureSensed(calculateAverageTemperatureSensed(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        weatherDto.setPressure(calculateAveragePressure(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        weatherDto.setHumidity(calculateAverageHumidity(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        weatherDto.setWindSpeed(calculateAverageWindSpeed(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        weatherDto.setWindDegrees(calculateAverageWindDegrees(weatherFromOpenWeatherApi,
                weatherFromDarkSkyApi, weatherFromWeatherBit));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        weatherDto.setDateTime(dtf.format(now).toString());
        return weatherDto;
    }

    public Weather weatherFromDtoToEntity(WeatherDto weatherDto) {
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

    public WeatherDto weatherFromEntityToDto(Weather weather) {
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

    public WeatherDtoForCityList weatherFromEntityToWeatherDtoForCityList(Weather weather) {
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

    private float calculateAverageTemperature(WeatherFromOpenWeatherApi openWeather,
                                              WeatherFromDarkSkyApi darkSky,
                                              WeatherFromWeatherBit weatherBit) {
        float result = 0;
        result = (openWeather.getMain().getTemperature()
                + darkSky.getCurrently().getTemperature()
                + weatherBit.getData()[0].getTemp()) / 3;
        return result;
    }

    private float calculateAverageTemperatureSensed(WeatherFromOpenWeatherApi openWeather,
                                                    WeatherFromDarkSkyApi darkSky,
                                                    WeatherFromWeatherBit weatherBit) {
        float result = 0;
        result = (openWeather.getMain().getTemperatureFeelsLike()
                + darkSky.getCurrently().getApparentTemperature()
                + weatherBit.getData()[0].getAppTemp()) / 3;
        return result;
    }

    private int calculateAveragePressure(WeatherFromOpenWeatherApi openWeather,
                                         WeatherFromDarkSkyApi darkSky,
                                         WeatherFromWeatherBit weatherBit) {
        int result = 0;
        result = (openWeather.getMain().getPressure()
                + darkSky.getCurrently().getPressure()
                + (int) weatherBit.getData()[0].getPres()) / 3;
        return result;
    }

    private int calculateAverageHumidity(WeatherFromOpenWeatherApi openWeather,
                                         WeatherFromDarkSkyApi darkSky,
                                         WeatherFromWeatherBit weatherBit) {
        int result = 0;
        result = (openWeather.getMain().getHumidity()
                + (int) (darkSky.getCurrently().getHumidity() * 100)
                + weatherBit.getData()[0].getHumidity()) / 3;
        return result;
    }

    private float calculateAverageWindSpeed(WeatherFromOpenWeatherApi openWeather,
                                         WeatherFromDarkSkyApi darkSky,
                                         WeatherFromWeatherBit weatherBit) {
        float result = 0;
        result = (openWeather.getWind().getSpeed()
                + darkSky.getCurrently().getWindSpeed()
                + weatherBit.getData()[0].getWindSpeed()) / 3;
        return result;
    }

    private int calculateAverageWindDegrees(WeatherFromOpenWeatherApi openWeather,
                                            WeatherFromDarkSkyApi darkSky,
                                            WeatherFromWeatherBit weatherBit) {
        int result = 0;
        result = (openWeather.getWind().getDegrees()
                + weatherBit.getData()[0].getWindDir()) / 2;
        return result;
    }

}
