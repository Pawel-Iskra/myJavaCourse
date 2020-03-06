package com.local_weather_API.external_APIs.darksky.model;

public class WeatherFromDarkSkyApi {

    private Currently currently;

    public WeatherFromDarkSkyApi() {
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }
}
