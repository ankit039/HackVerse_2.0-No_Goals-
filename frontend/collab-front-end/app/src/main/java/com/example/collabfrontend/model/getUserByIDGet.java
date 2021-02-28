package com.example.collabfrontend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getUserByIDGet {

    @SerializedName("sucess")
    @Expose
    private boolean sucess;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("rows")
    @Expose
    private List<User> rows = null;

    public getUserByIDGet() {
    }

    public getUserByIDGet(boolean sucess, String msg, List<User> rows) {
        super();
        this.sucess = sucess;
        this.msg = msg;
        this.rows = rows;
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

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "getUserByIDGet{" +
                "sucess=" + sucess +
                ", msg='" + msg + '\'' +
                ", rows=" + rows +
                '}';
    }
}
