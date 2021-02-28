package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.User;
import com.example.collabfrontend.model.getUserByIDGet;
import com.example.collabfrontend.model.getUserByIDPost;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchedActivity extends AppCompatActivity {

    Button search_more, go_to_profile;
    String one, two;
    User firstUser = null;
    User secondUser = null;
    ImageView oneI, twoI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        init();
        Intent intent = getIntent();
        one = intent.getExtras().getString("one");
        two = intent.getExtras().getString("two");

        LoginUserGet currUser = Paper.book().read("currUser");

        getUserByIDPost curr = new getUserByIDPost(currUser.getToken(), one);
        Log.e("this34", one);
        Call<getUserByIDGet> get = RetrofitClient.getInstance().getMyApi().getuserbyuid(curr);
        get.enqueue(new Callback<getUserByIDGet>() {
            @Override
            public void onResponse(Call<getUserByIDGet> call, Response<getUserByIDGet> response) {
                Log.e("this", response.body().toString());
                firstUser = response.body().getRows().get(0);

                if(firstUser.getImage().equals("")){
                    oneI.setImageResource(R.drawable.add_pic);
                }else{
                    byte[] decodedString = Base64.decode(firstUser.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    oneI.setImageBitmap(decodedByte);
                }
            }

            @Override
            public void onFailure(Call<getUserByIDGet> call, Throwable t) {

            }
        });

        getUserByIDPost curr1 = new getUserByIDPost(currUser.getToken(), two);
        Log.e("this34", two);
        Call<getUserByIDGet> get2 = RetrofitClient.getInstance().getMyApi().getuserbyuid(curr1);
        get2.enqueue(new Callback<getUserByIDGet>() {
            @Override
            public void onResponse(Call<getUserByIDGet> call, Response<getUserByIDGet> response) {
                Log.e("this", response.body().toString());
                secondUser = response.body().getRows().get(0);

                if(secondUser.getImage().equals("")){
                    twoI.setImageResource(R.drawable.add_pic);
                }else{
                    byte[] decodedString = Base64.decode(secondUser.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    twoI.setImageBitmap(decodedByte);
                }
            }

            @Override
            public void onFailure(Call<getUserByIDGet> call, Throwable t) {

            }
        });






        search_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchedActivity.this, MainActivity.class));
                finish();
            }
        });

        go_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchedActivity.this, ProfileActivity.class));
                finish();
            }
        });
    }

    private void init() {
        search_more = findViewById(R.id.search_more);
        go_to_profile = findViewById(R.id.go_to_profile);
        oneI = findViewById(R.id.profile_pic_1);
        twoI = findViewById(R.id.profile_pic_2);
    }
}