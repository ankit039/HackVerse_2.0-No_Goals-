package com.example.collabfrontend.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserGet {

    @SerializedName("sucess")
    @Expose
    private boolean sucess;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("uid")
    @Expose
    private long uid;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("connect")
    @Expose
    private String connect;
    @SerializedName("reject")
    @Expose
    private String reject;
    @SerializedName("accept")
    @Expose
    private String accept;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expiration")
    @Expose
    private String expiration;

    public LoginUserGet() {
    }

    public LoginUserGet(boolean sucess, String msg, long uid, String userName, String fullName, String emailId, String image, String skills, String connect, String reject, String accept, String token, String expiration) {
        super();
        this.sucess = sucess;
        this.msg = msg;
        this.uid = uid;
        this.userName = userName;
        this.fullName = fullName;
        this.emailId = emailId;
        this.image = image;
        this.skills = skills;
        this.connect = connect;
        this.reject = reject;
        this.accept = accept;
        this.token = token;
        this.expiration = expiration;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "LoginUserGet{" +
                "sucess=" + sucess +
                ", msg='" + msg + '\'' +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", image='" + image + '\'' +
                ", skills='" + skills + '\'' +
                ", connect='" + connect + '\'' +
                ", reject='" + reject + '\'' +
                ", accept='" + accept + '\'' +
                ", token='" + token + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }
}