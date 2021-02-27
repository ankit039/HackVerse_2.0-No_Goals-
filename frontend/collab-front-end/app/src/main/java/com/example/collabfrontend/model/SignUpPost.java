package com.example.collabfrontend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpPost {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private String password;
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

    public SignUpPost() {
    }

    public SignUpPost(String username, String fullName, String emailId, String password, String image, String skills, String connect, String accept, String reject) {
        super();
        this.username = username;
        this.fullName = fullName;
        this.emailId = emailId;
        this.password = password;
        this.image = image;
        this.skills = skills;
        this.connect = connect;
        this.accept = accept;
        this.reject = reject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "SignUpPost{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", skills='" + skills + '\'' +
                ", connect='" + connect + '\'' +
                ", accept='" + accept + '\'' +
                ", reject='" + reject + '\'' +
                '}';
    }
}
