package com.example.collabfrontend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePost {
    @SerializedName("token")
    @Expose
    private String token;
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
    @SerializedName("accept")
    @Expose
    private String accept;
    @SerializedName("reject")
    @Expose
    private String reject;
    @SerializedName("uid")
    @Expose
    private long uid;

    public UpdatePost() {
    }

    public UpdatePost(String token, String userName, String fullName, String emailId, String image, String skills, String connect, String accept, String reject, long uid) {
        super();
        this.token = token;
        this.userName = userName;
        this.fullName = fullName;
        this.emailId = emailId;
        this.image = image;
        this.skills = skills;
        this.connect = connect;
        this.accept = accept;
        this.reject = reject;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UpdatePost{" +
                "token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", image='" + image + '\'' +
                ", skills='" + skills + '\'' +
                ", connect='" + connect + '\'' +
                ", accept='" + accept + '\'' +
                ", reject='" + reject + '\'' +
                ", uid=" + uid +
                '}';
    }
}
