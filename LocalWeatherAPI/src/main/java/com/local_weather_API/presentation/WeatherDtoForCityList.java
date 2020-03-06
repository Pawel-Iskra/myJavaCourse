package com.local_weather_API.presentation;

public class WeatherDtoForCityList {

    private String dateTime;
    private String mainWeatherType;
    private String weatherDescription;
    private float temperature;
    private float temperatureSensed;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private int windDegrees;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMainWeatherType() {
        return mainWeatherType;
    }

    public void setMainWeatherType(String mainWeatherType) {
        this.mainWeatherType = mainWeatherType;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperatureSensed() {
        return temperatureSensed;
    }

    public void setTemperatureSensed(float temperatureSensed) {
        this.temperatureSensed = temperatureSensed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }
}
