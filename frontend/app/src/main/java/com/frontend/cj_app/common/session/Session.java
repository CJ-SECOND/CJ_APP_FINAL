package com.frontend.cj_app.common.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserSeq(int userSeq) {
        prefs.edit().putInt("userSeq", userSeq).commit();
    }

    public int getUserSeq() {
        int userSeq = prefs.getInt("userSeq", 1);
        return userSeq;
    }
}