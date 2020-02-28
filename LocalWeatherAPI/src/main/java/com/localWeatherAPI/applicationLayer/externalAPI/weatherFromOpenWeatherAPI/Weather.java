package com.localWeatherAPI.applicationLayer.externalAPI.weatherFromOpenWeatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

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
