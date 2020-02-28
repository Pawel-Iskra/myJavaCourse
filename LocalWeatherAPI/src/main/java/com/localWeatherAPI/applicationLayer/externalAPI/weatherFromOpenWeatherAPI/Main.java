package com.localWeatherAPI.applicationLayer.externalAPI.weatherFromOpenWeatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
    private float temperature;
    private float temperatureFeelsLike;
    private int pressure;
    private int humidity;

    @JsonProperty("temp")
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("feels_like")
    public float getTemperatureFeelsLike() {
        return temperatureFeelsLike;
    }

    public void setTemperatureFeelsLike(float temperatureFeelsLike) {
        this.temperatureFeelsLike = temperatureFeelsLike;
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
}
