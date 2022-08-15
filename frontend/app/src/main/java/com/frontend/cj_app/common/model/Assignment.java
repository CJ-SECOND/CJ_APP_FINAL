package com.frontend.cj_app.common.model;

import java.io.Serializable;

public class Assignment implements Serializable {
    private String coury_to_address;
    private String coury_to_name;
    private String coury_from_address;
    private String coury_from_name;
    private String area;
    private String category;
    private String weight;
    private String size;
    private String coury_date;

    public Assignment(){}

    public Assignment(String coury_to_address, String coury_to_name, String coury_from_address, String coury_from_name, String area, String category, String weight, String size, String coury_date) {
        this.coury_to_address = coury_to_address;
        this.coury_to_name = coury_to_name;
        this.coury_from_address = coury_from_address;
        this.coury_from_name = coury_from_name;
        this.area = area;
        this.category = category;
        this.weight = weight;
        this.size = size;
        this.coury_date = coury_date;
    }

    public String getCoury_to_address() {
        return coury_to_address;
    }

    public void setCoury_to_address(String coury_to_address) {
        this.coury_to_address = coury_to_address;
    }

    public String getCoury_to_name() {
        return coury_to_name;
    }

    public void setCoury_to_name(String coury_to_name) {
        this.coury_to_name = coury_to_name;
    }

    public String getCoury_from_address() {
        return coury_from_address;
    }

    public void setCoury_from_address(String coury_from_address) {
        this.coury_from_address = coury_from_address;
    }

    public String getCoury_from_name() {
        return coury_from_name;
    }

    public void setCoury_from_name(String coury_from_name) {
        this.coury_from_name = coury_from_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCoury_date() {
        return coury_date;
    }

    public void setCoury_date(String coury_date) {
        this.coury_date = coury_date;
    }
}
