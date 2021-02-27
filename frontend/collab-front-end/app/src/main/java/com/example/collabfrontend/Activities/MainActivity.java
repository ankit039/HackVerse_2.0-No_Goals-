package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.AllUserGet;
import com.example.collabfrontend.model.AllUserPost;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.User;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LoginUserGet currUser;
    List<User> allUsers = new ArrayList<>();
    List<User> selectUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currUser = Paper.book().read("currUser");
        AllUserPost getdata = new AllUserPost(currUser.getToken(), currUser.getUserName());
        Call<AllUserGet> call = RetrofitClient.getInstance().getMyApi().getAllData(getdata);
        call.enqueue(new Callback<AllUserGet>() {
            @Override
            public void onResponse(Call<AllUserGet> call, Response<AllUserGet> response) {
                allUsers = response.body().getRows();
                Match();
            }

            @Override
            public void onFailure(Call<AllUserGet> call, Throwable t) {

            }
        });

        

    }

    private void Match() {
        String skillsCurr = currUser.getSkills();

        for(int i = 0; i < allUsers.size(); i++){
            for(int j = 0; j < skillsCurr.length(); j++){
                if(skillsCurr.charAt(j) != ','){
                    String compare = String.valueOf(skillsCurr.charAt(j));
                    if(allUsers.get(i).getSkills().contains(compare)){
                        selectUsers.add(allUsers.get(i));
                        break;
                    }
                }
            }
        }
    }
}