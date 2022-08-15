package com.frontend.cj_app.common.payload;

import com.frontend.cj_app.common.model.Coury;

import java.util.List;

public class Assignmented_Response {
    private List<Coury> courList;

    public Assignmented_Response(List<Coury> courList) {
        this.courList = courList;
    }

    public List<Coury> getCourList() {
        return courList;
    }

    public void setCourList(List<Coury> courList) {
        this.courList = courList;
    }
}
