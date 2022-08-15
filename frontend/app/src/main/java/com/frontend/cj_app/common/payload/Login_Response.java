package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Login_Response {

    @SerializedName("userSeq")
    private int userSeq;

    @SerializedName("REPL_CD")
    private String REPL_CD;

    @SerializedName("REPL_MSG")
    private String REPL_MSG;

    public Login_Response() {}

    public Login_Response(int userSeq, String REPL_CD, String REPL_MSG) {
        this.userSeq = userSeq;
        this.REPL_CD = REPL_CD;
        this.REPL_MSG = REPL_MSG;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
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
