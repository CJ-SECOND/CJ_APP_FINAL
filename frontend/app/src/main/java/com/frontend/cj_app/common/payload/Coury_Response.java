package com.frontend.cj_app.common.payload;

import com.frontend.cj_app.common.model.Coury_Result;
import com.google.gson.annotations.SerializedName;

public class Coury_Response {

    @SerializedName("total")
    private Coury_Result total;

    @SerializedName("wrong")
    private Coury_Result wrong;

    @SerializedName("complete")
    private Coury_Result complete;

    @SerializedName("damage")
    private Coury_Result damage;

    @SerializedName("REPL_CD")
    private String REPL_CD;

    @SerializedName("REPL_MSG")
    private String REPL_MSG;

    public Coury_Result getTotal() {
        return total;
    }

    public void setTotal(Coury_Result total) {
        this.total = total;
    }

    public Coury_Result getWrong() {
        return wrong;
    }

    public void setWrong(Coury_Result wrong) {
        this.wrong = wrong;
    }

    public Coury_Result getComplete() {
        return complete;
    }

    public void setComplete(Coury_Result complete) {
        this.complete = complete;
    }

    public Coury_Result getDamage() {
        return damage;
    }

    public void setDamage(Coury_Result damage) {
        this.damage = damage;
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