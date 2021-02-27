package com.example.collabfrontend.Interface;

import com.example.collabfrontend.model.AllUserGet;
import com.example.collabfrontend.model.AllUserPost;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.LoginUserPost;
import com.example.collabfrontend.model.SignUpGet;
import com.example.collabfrontend.model.SignUpPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "https://ankit039-collab.herokuapp.com/api/";

    @POST("login")
    Call<LoginUserGet> loginUser(@Body LoginUserPost post);

    @POST("getuserbyskills")
    Call<AllUserGet> getAllData(@Body AllUserPost post);

    @POST("register")
    Call<SignUpGet> signupUser(@Body SignUpPost post);

}
