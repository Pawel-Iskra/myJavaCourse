package com.local_weather_API.external_APIs.weatherbit.model;

public class WeatherFromWeatherBit {
    
    private Data[] data;

    public WeatherFromWeatherBit() {
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }
}
