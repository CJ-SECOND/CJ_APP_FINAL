package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Login_Request {

    @SerializedName("USER_ID")
    private String user_id;

    @SerializedName("USER_PASSWORD")
    private String user_password;

    public Login_Request() {}

    public Login_Request(String user_id, String user_password) {
        this.user_id = user_id;
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
