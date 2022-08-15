package com.frontend.cj_app.common.model.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPath {
    @SerializedName("route")
    @Expose
    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
