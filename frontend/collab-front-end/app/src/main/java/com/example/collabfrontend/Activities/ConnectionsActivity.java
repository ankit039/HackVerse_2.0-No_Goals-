package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.collabfrontend.Adapters.ProfileListAdapter;
import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.User;
import com.example.collabfrontend.model.getUserByIDGet;
import com.example.collabfrontend.model.getUserByIDPost;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionsActivity extends AppCompatActivity {

    LoginUserGet currUser;
    RecyclerView recycle;
    ProfileListAdapter adapter;
    List<String> connectedUID = new ArrayList<>();
    List<User> allConnectedUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted);

        Paper.init(this);
        currUser = Paper.book().read("currUser");
        Log.e("this34", currUser.toString());

        init();

        String connectionUser = currUser.getConnect();
        for(int i = 0; i < connectionUser.length(); i++){
            if(connectionUser.charAt(i) != ','){
                connectedUID.add(String.valueOf(connectionUser.charAt(i)));
            }
        }



        for(int i = 0; i < connectedUID.size(); i++){
            getUserByIDPost curr = new getUserByIDPost(currUser.getToken(), connectedUID.get(i));
            Log.e("this", curr.toString());
            Call<getUserByIDGet> get = RetrofitClient.getInstance().getMyApi().getuserbyuid(curr);
            get.enqueue(new Callback<getUserByIDGet>() {
                @Override
                public void onResponse(Call<getUserByIDGet> call, Response<getUserByIDGet> response) {
                    if(response.body().getRows().size() == 1) {
                        User curr = response.body().getRows().get(0);
                        allConnectedUsers.add(curr);
                        adapter.setData(allConnectedUsers);
                    }
                }

                @Override
                public void onFailure(Call<getUserByIDGet> call, Throwable t) {

                }
            });
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(adapter);

    }

    private void init() {
        recycle = findViewById(R.id.rec);
        adapter = new ProfileListAdapter(this);
    }
}