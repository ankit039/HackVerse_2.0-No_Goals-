package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collabfrontend.Adapters.CardStackCallback;
import com.example.collabfrontend.Adapters.UserAdapter;
import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.AllUserGet;
import com.example.collabfrontend.model.AllUserPost;
import com.example.collabfrontend.model.LoginUserGet;
import com.example.collabfrontend.model.User;
import com.squareup.picasso.Picasso;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

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
    ImageView profile_pic, refresh;
    Toolbar toolbar;
    UserAdapter adapter;
    CardStackLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        Init();

        currUser = Paper.book().read("currUser");
        AllUserPost getdata = new AllUserPost(currUser.getToken(), currUser.getUserName());
        Call<AllUserGet> call = RetrofitClient.getInstance().getMyApi().getAllData(getdata);
        call.enqueue(new Callback<AllUserGet>() {
            @Override
            public void onResponse(Call<AllUserGet> call, Response<AllUserGet> response) {
                try{
                    allUsers = response.body().getRows();
                    Match();

                    //set Image
                    if(currUser.getImage().equals("")){
                        profile_pic.setImageResource(R.drawable.add_pic);
                    }else{
                        byte[] decodedString = Base64.decode(currUser.getImage(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        profile_pic.setImageBitmap(decodedByte);
                    }
                    adapter.setData(selectUsers);
                }catch (Exception e){
                    Log.e("this", e.toString());
                    Toast.makeText(MainActivity.this, "Some Error Occured!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AllUserGet> call, Throwable t) {

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currUser = Paper.book().read("currUser");
                AllUserPost getdata = new AllUserPost(currUser.getToken(), currUser.getUserName());
                Call<AllUserGet> call = RetrofitClient.getInstance().getMyApi().getAllData(getdata);
                call.enqueue(new Callback<AllUserGet>() {
                    @Override
                    public void onResponse(Call<AllUserGet> call, Response<AllUserGet> response) {
                        try{
                            allUsers = response.body().getRows();
                            Match();

                            //set Image
                            if(currUser.getImage().equals("")){
                                profile_pic.setImageResource(R.drawable.add_pic);
                            }else{
                                byte[] decodedString = Base64.decode(currUser.getImage(), Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                profile_pic.setImageBitmap(decodedByte);
                            }
                            adapter.setData(selectUsers);
                        }catch (Exception e){
                            Log.e("this", e.toString());
                            Toast.makeText(MainActivity.this, "Some Error Occured!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AllUserGet> call, Throwable t) {

                    }
                });
            }
        });

        adapter = new UserAdapter(this, selectUsers);
        CardStackView cardStackView = findViewById(R.id.rv);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {
                if(direction == Direction.Left){

                }

                if(direction == Direction.Right){

                }

                if(manager.getTopPosition() == selectUsers.size()){
                    Toast.makeText(MainActivity.this, "No more Users left. Please refresh to get new users!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());

        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }

    private void Init() {
        profile_pic = findViewById(R.id.profile_pic);
        refresh = findViewById(R.id.refresh);
    }

    private void Match() {
        String skillsCurr = currUser.getSkills();
        selectUsers.clear();
        for(int i = 0; i < allUsers.size(); i++){
            for(int j = 0; j < skillsCurr.length(); j++){
                if(skillsCurr.charAt(j) != ','){
                    String compare = String.valueOf(skillsCurr.charAt(j));
                    if(allUsers.get(i).getSkills().contains(compare)){
                        if(!allUsers.get(i).getReject().contains(String.valueOf(currUser.getUid()))){
                            selectUsers.add(allUsers.get(i));
                            break;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < selectUsers.size(); i++){
            Log.e("this", selectUsers.get(i).toString());
        }

    }
}