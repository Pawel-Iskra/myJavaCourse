package com.local_weather_API.external_APIs.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {


    private String mainWeatherType;
    private String description;

    @JsonProperty("main")
    public String getMainWeatherType() {
        return mainWeatherType;
    }

    public void setMainWeatherType(String mainWeatherType) {
        this.mainWeatherType = mainWeatherType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
