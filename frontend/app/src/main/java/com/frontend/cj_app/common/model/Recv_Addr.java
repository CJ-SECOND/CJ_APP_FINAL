package com.frontend.cj_app.common.model;

import com.google.gson.annotations.SerializedName;

public class Recv_Addr {

    private String couryToAddress;

    private String couryCondition;

    private String couryToName;

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
