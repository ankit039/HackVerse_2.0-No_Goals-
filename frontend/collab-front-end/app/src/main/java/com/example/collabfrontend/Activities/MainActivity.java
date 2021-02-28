package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.collabfrontend.model.UpdateGet;
import com.example.collabfrontend.model.UpdatePost;
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
    CardStackView cardStackView;
    TextView empy_view;

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
                try {
                    allUsers = response.body().getRows();
                    Match();

                    //set Image
                    if (currUser.getImage().equals("")) {
                        profile_pic.setImageResource(R.drawable.add_pic);
                    } else {
                        byte[] decodedString = Base64.decode(currUser.getImage(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        profile_pic.setImageBitmap(decodedByte);
                    }
                    adapter.setData(selectUsers);
                    if (selectUsers.isEmpty()) {
                        cardStackView.setVisibility(View.GONE);
                        empy_view.setVisibility(View.VISIBLE);
                    } else {
                        cardStackView.setVisibility(View.VISIBLE);
                        empy_view.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
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
                UpdatePost post = new UpdatePost(currUser.getToken(), currUser.getUserName(), currUser.getFullName(), currUser.getEmailId(), currUser.getImage(), currUser.getSkills(), currUser.getConnect(), currUser.getAccept(), currUser.getReject(), currUser.getUid());
                dataUpdate(post);
                currUserLocalUpdate();
                getAllUser();
            }
        });

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        adapter = new UserAdapter(this, selectUsers);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Left) {
                    swipeLeft(manager.getTopPosition() - 1);
                }

                if (direction == Direction.Right) {
                    swipeRight(manager.getTopPosition() - 1);
                }

                if (manager.getTopPosition() == selectUsers.size()) {
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

    private void dataUpdate(UpdatePost post) {
        Call<UpdateGet> call = RetrofitClient.getInstance().getMyApi().updateData(post);
        call.enqueue(new Callback<UpdateGet>() {
            @Override
            public void onResponse(Call<UpdateGet> call, Response<UpdateGet> response) {
                if (response.body().isSucess()) {
                    Log.e("this0", response.body().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Failed, Try Again!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateGet> call, Throwable t) {

            }
        });
    }

    private void currUserLocalUpdate() {
        Paper.book().write("currUser", currUser);
    }

    private void getAllUser() {
        currUser = Paper.book().read("currUser");
        AllUserPost getdata = new AllUserPost(currUser.getToken(), currUser.getUserName());
        Call<AllUserGet> call = RetrofitClient.getInstance().getMyApi().getAllData(getdata);
        call.enqueue(new Callback<AllUserGet>() {
            @Override
            public void onResponse(Call<AllUserGet> call, Response<AllUserGet> response) {
                try {
                    allUsers = response.body().getRows();
                    Match();

                    //set Image
                    if (currUser.getImage().equals("")) {
                        profile_pic.setImageResource(R.drawable.add_pic);
                    } else {
                        byte[] decodedString = Base64.decode(currUser.getImage(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        profile_pic.setImageBitmap(decodedByte);
                    }
                    adapter.setData(selectUsers);
                    if (selectUsers.isEmpty()) {
                        cardStackView.setVisibility(View.GONE);
                        empy_view.setVisibility(View.VISIBLE);
                    } else {
                        cardStackView.setVisibility(View.VISIBLE);
                        empy_view.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.e("this", e.toString());
                    Toast.makeText(MainActivity.this, "Some Error Occured!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AllUserGet> call, Throwable t) {

            }
        });
    }

    private void swipeRight(int i) {
        if (adapter.getData().get(i).getAccept().contains(String.valueOf(currUser.getUid()))) {
            String currUserConnect = currUser.getConnect();
            currUserConnect += adapter.getData().get(i).getUid();
            currUserConnect += ",";
            currUser.setConnect(currUserConnect);
            UpdatePost post = new UpdatePost(currUser.getToken(), currUser.getUserName(), currUser.getFullName(), currUser.getEmailId(), currUser.getImage(), currUser.getSkills(), currUser.getConnect(), currUser.getAccept(), currUser.getReject(), currUser.getUid());
            dataUpdate(post);

            String otherUserConnect = adapter.getData().get(i).getConnect();
            otherUserConnect += currUser.getUid();
            otherUserConnect += ",";
            adapter.getData().get(i).setConnect(otherUserConnect);
            UpdatePost post2 = new UpdatePost(currUser.getToken(), adapter.getData().get(i).getUserName(), adapter.getData().get(i).getFullName(), adapter.getData().get(i).getEmailId(), adapter.getData().get(i).getImage(), adapter.getData().get(i).getSkills(), adapter.getData().get(i).getConnect(), adapter.getData().get(i).getAccept(), adapter.getData().get(i).getReject(), adapter.getData().get(i).getUid());
            dataUpdate(post2);

            currUserLocalUpdate();
            getAllUser();
            Toast.makeText(this, "Matched!!", Toast.LENGTH_SHORT).show();
            //start new activity
            Intent intent = new Intent(MainActivity.this, MatchedActivity.class);
            intent.putExtra("one", String.valueOf(currUser.getUid()));
            intent.putExtra("two", String.valueOf(adapter.getData().get(i).getUid()));
            startActivity(intent);
            finish();
        } else {
            String currUserAccept = currUser.getAccept();
            currUserAccept += adapter.getData().get(i).getUid();
            currUserAccept += ",";
            currUser.setAccept(currUserAccept);
        }
    }

    private void swipeLeft(int i) {
        String currUserReject = currUser.getReject();
        currUserReject += adapter.getData().get(i).getUid();
        currUserReject += ",";
        currUser.setReject(currUserReject);
    }

    private void Init() {
        profile_pic = findViewById(R.id.profile_pic);
        refresh = findViewById(R.id.refresh);
        cardStackView = findViewById(R.id.rv);
        empy_view = findViewById(R.id.empty_view);
    }

    private void Match() {
        String skillsCurr = currUser.getSkills();
        selectUsers.clear();

        for (int i = 0; i < allUsers.size(); i++) {
            for (int j = 0; j < skillsCurr.length(); j++) {
                if (skillsCurr.charAt(j) != ',') {
                    String compare = String.valueOf(skillsCurr.charAt(j));
                    if (allUsers.get(i).getSkills().contains(compare)) {
                        if (!allUsers.get(i).getReject().contains(String.valueOf(currUser.getUid()))) {
                            if (!currUser.getReject().contains(String.valueOf(allUsers.get(i).getUid()))) {
                                if (!currUser.getConnect().contains(String.valueOf(allUsers.get(i).getUid()))) {
                                    if (!currUser.getAccept().contains(String.valueOf(allUsers.get(i).getUid()))) {
                                        selectUsers.add(allUsers.get(i));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < selectUsers.size(); i++) {
            Log.e("this", selectUsers.get(i).toString());
        }

    }
}