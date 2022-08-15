package com.frontend.cj_app.common.model;

import java.io.Serializable;

public class Coury implements Serializable {
    private String recvName;
    private String recvAddr;
    private String couryCondition;
    private String expressState;

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvAddr() {
        return recvAddr;
    }

    public void setRecvAddr(String recvAddr) {
        this.recvAddr = recvAddr;
    }

    public String getCouryCondition() {
        return couryCondition;
    }

    public void setCouryCondition(String couryCondition) {
        this.couryCondition = couryCondition;
    }

    public String getExpressState() {
        return expressState;
    }

    public void setExpressState(String expressState) {
        this.expressState = expressState;
    }
}
