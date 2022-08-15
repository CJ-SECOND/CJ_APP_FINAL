package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Assignment_Request {

    @SerializedName("area")
    private String area;

    @SerializedName("priority")
    private String priority;

    @SerializedName("count")
    private int count;

    @SerializedName("workTime")
    private String workTime;

    public Assignment_Request(){}

    public Assignment_Request(String area, String priority, int count, String workTime) {
        this.area = area;
        this.priority = priority;
        this.count = count;
        this.workTime = workTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}
