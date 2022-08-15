package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Complete_Request {
    @SerializedName("couryCondition")
    private String area;

    public Complete_Request(){}

    public Complete_Request(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
