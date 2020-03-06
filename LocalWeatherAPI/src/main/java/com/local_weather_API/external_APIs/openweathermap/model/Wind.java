package com.local_weather_API.external_APIs.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {

    private float speed;
    private int degrees;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @JsonProperty("deg")
    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
