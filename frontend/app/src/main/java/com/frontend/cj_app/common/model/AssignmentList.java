package com.frontend.cj_app.common.model;

import java.io.Serializable;

public class AssignmentList implements Serializable {
    private String packageName;
    private String classSize;
    private String classWeight;
    private String classTraffic;
    private int pickupTime;
    private int totalFee;
    private int totalScore;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassSize() {
        return classSize;
    }

    public void setClassSize(String classSize) {
        this.classSize = classSize;
    }

    public String getClassWeight() {
        return classWeight;
    }

    public void setClassWeight(String classWeight) {
        this.classWeight = classWeight;
    }

    public String getClassTraffic() {
        return classTraffic;
    }

    public void setClassTraffic(String classTraffic) {
        this.classTraffic = classTraffic;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
