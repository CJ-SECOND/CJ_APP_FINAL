package com.frontend.cj_app.common.model;

import java.io.Serializable;
import java.util.List;

public class Coury_Result implements Serializable {
    private List<Coury_Cnt> result;

    public List<Coury_Cnt> getResult() {
        return result;
    }

    public void setResult(List<Coury_Cnt> result) {
        this.result = result;
    }
}
