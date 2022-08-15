package com.frontend.cj_app.common.model;

public class TASK_CHILD_LIST {

    private String area;
    private String datetime;
    private String timeState;

    public TASK_CHILD_LIST(){}

    public TASK_CHILD_LIST(String area, String datetime, String timeState) {
        this.area = area;
        this.datetime = datetime;
        this.timeState = timeState;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTimeState() {
        return timeState;
    }

    public void setTimeState(String timeState) {
        this.timeState = timeState;
    }
}
