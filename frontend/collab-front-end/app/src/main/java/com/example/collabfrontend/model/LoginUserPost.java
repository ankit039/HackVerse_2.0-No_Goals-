package com.example.collabfrontend.model;

import androidx.core.app.RemoteInput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserPost {

    @SerializedName("credential")
    @Expose
    private String credential;
    @SerializedName("password")
    @Expose
    private String password;

    public LoginUserPost(String credential, String password) {
        this.credential = credential;
        this.password = password;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUserPost{" +
                "credential='" + credential + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
