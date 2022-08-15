package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CouryToAddress_Response {

public CouryToAddress_Response(){}

    @SerializedName("recvAddrList")
    @Expose
    private List<Coury_map> recvAddrList;

    @SerializedName("REPL_CD")
    @Expose
    private String REPL_CD;

    @SerializedName("REPL_MSG")
    @Expose
    private String REPL_MSG;

    public CouryToAddress_Response(List<Coury_map> recvAddrList, String REPL_CD, String REPL_MSG) {
        this.recvAddrList = recvAddrList;
        this.REPL_CD = REPL_CD;
        this.REPL_MSG = REPL_MSG;
    }

    public List<Coury_map> getRecvAddrList() {
        return recvAddrList;
    }

    public void setRecvAddrList(List<Coury_map> recvAddrList) {
        this.recvAddrList = recvAddrList;
    }

    public String getREPL_CD() {
        return REPL_CD;
    }

    public void setREPL_CD(String REPL_CD) {
        this.REPL_CD = REPL_CD;
    }

    public String getREPL_MSG() {
        return REPL_MSG;
    }

    public void setREPL_MSG(String REPL_MSG) {
        this.REPL_MSG = REPL_MSG;
    }
}
