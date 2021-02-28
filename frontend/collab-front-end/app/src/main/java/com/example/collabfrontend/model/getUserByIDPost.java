package com.example.collabfrontend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getUserByIDPost {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("uid")
    @Expose
    private String uid;

    public getUserByIDPost() {
    }

    public getUserByIDPost(String token, String uid) {
        super();
        this.token = token;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "getUserByIDPost{" +
                "token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
