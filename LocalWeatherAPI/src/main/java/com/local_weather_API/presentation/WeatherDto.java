package com.local_weather_API.presentation;

public class WeatherDto {

    private String cityName;
    private String countryName;
    private float longitude;
    private float latitude;
    private String mainWeatherType;
    private String weatherDescription;
    private float temperature;
    private float temperatureSensed;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private int windDegrees;
    private String dateTime;

    public WeatherDto() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
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

        this.temperature = Math.round(temperature * 100.0F) / 100.0F;
    }

    public float getTemperatureSensed() {
        return temperatureSensed;
    }

    public void setTemperatureSensed(float temperatureSensed) {

        this.temperatureSensed = Math.round(temperatureSensed * 100.0F) / 100.0F;
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
        this.windSpeed = Math.round(windSpeed * 100.0F) / 100.0F;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
