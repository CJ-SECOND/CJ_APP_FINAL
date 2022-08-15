package com.frontend.cj_app.common.model.map;

import com.google.gson.annotations.SerializedName;

public class Summary {
    public Summary(){
    }

    public Summary(int distance, int duration) {
        this.distance = distance;
        this.duration = duration;
    }

    @SerializedName("distance")
    private int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    @SerializedName("duration")
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}


