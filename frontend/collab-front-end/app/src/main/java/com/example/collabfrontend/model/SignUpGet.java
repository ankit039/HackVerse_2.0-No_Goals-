package com.example.collabfrontend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpGet {
    @SerializedName("sucess")
    @Expose
    private boolean sucess;
    @SerializedName("msg")
    @Expose
    private String msg;

    public SignUpGet() {
    }

    public SignUpGet(boolean sucess, String msg) {
        super();
        this.sucess = sucess;
        this.msg = msg;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SignUpGet{" +
                "sucess=" + sucess +
                ", msg='" + msg + '\'' +
                '}';
    }
}
