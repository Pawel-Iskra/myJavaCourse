package com.local_weather_API.external_APIs.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {

    private float longitude;
    private float latitude;

    @JsonProperty("lon")
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("lat")
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
