package com.frontend.cj_app.common.model.map;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Option {

    @SerializedName("summary")
    private Summary summary;
    @SerializedName("path")
    private List<List<Double>> path;

    public Option(Summary summary, List<List<Double>> path) {
        this.summary = summary;
        this.path = path;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<List<Double>> getPath() {
        return path;
    }

    public void setPath(List<List<Double>> path) {
        this.path = path;
    }
}
