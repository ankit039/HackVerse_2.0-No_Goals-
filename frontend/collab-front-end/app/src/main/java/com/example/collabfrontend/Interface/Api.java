package com.example.collabfrontend.Interface;

import com.example.collabfrontend.model.AllUserGet;
import com.example.collabfrontend.model.AllUserPost;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.LoginUserPost;
import com.example.collabfrontend.model.SignUpGet;
import com.example.collabfrontend.model.SignUpPost;
import com.example.collabfrontend.model.UpdateGet;
import com.example.collabfrontend.model.UpdatePost;
import com.example.collabfrontend.model.getUserByIDGet;
import com.example.collabfrontend.model.getUserByIDPost;

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

    @POST("update")
    Call<UpdateGet> updateData(@Body UpdatePost post);

    @POST("getuserbyuid")
    Call<getUserByIDGet> getuserbyuid(@Body getUserByIDPost post);

}
