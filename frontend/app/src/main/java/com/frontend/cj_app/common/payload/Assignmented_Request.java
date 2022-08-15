package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Assignmented_Request {
    @SerializedName("packageName")
    private String packageName;

    @SerializedName("userSeq")
    private int userSeq;

    public Assignmented_Request(String packageName, int userSeq) {
        this.packageName = packageName;
        this.userSeq = userSeq;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }
}
