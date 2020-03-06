package com.local_weather_API.external_APIs.weatherbit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private int humidity;
    private float pres;
    private float windSpeed;
    private int windDir;
    private float temp;
    private float appTemp;

    public Data() {
    }

    @JsonProperty("rh")
    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPres() {
        return pres;
    }

    public void setPres(float pres) {
        this.pres = pres;
    }

    @JsonProperty("wind_spd")
    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("wind_dir")
    public int getWindDir() {
        return windDir;
    }

    public void setWindDir(int windDir) {
        this.windDir = windDir;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
    @JsonProperty("app_temp")
    public float getAppTemp() {
        return appTemp;
    }

    public void setAppTemp(float appTemp) {
        this.appTemp = appTemp;
    }
}
