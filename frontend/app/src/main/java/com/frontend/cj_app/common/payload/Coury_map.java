package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Coury_map {

    @SerializedName("COURY_TO_ADDRESS")
    private String couryToAddress;

    @SerializedName("COURY_CONDITION")
    private String couryCondition;

    @SerializedName("COURY_TO_NAME")
    private String couryToName;

    public Coury_map(String couryToAddress, String couryCondition, String couryToName) {
        this.couryToAddress = couryToAddress;
        this.couryCondition = couryCondition;
        this.couryToName = couryToName;
    }

    public String getCouryToAddress() {
        return couryToAddress;
    }

    public void setCouryToAddress(String couryToAddress) {
        this.couryToAddress = couryToAddress;
    }

    public String getCouryCondition() {
        return couryCondition;
    }

    public void setCouryCondition(String couryCondition) {
        this.couryCondition = couryCondition;
    }

    public String getCouryToName() {
        return couryToName;
    }

    public void setCouryToName(String couryToName) {
        this.couryToName = couryToName;
    }

}
