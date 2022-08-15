package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class CouryToAddress_Request {
    @SerializedName("userSeq")
    private int userSeq;

    @SerializedName("packageName")
    private String packageName;

    public CouryToAddress_Request(){}

    public CouryToAddress_Request(int userSeq, String packageName) {
        this.userSeq = userSeq;
        this.packageName = packageName;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
