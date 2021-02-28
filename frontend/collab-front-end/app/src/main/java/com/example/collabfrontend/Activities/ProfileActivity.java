package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collabfrontend.R;
import com.example.collabfrontend.model.LoginUserGet;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ProfileActivity extends AppCompatActivity {

    TextView logout, name, skills1, connections, accepted, rejected;
    ImageView logo;
    LoginUserGet currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Paper.init(this);
        init();

        connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ConnectionsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(ProfileActivity.this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        currUser = Paper.book().read("currUser");

        name.setText(currUser.getFullName());
        if(currUser.getImage().equals("")){
            logo.setImageResource(R.drawable.add_pic);
        }else{
            byte[] decodedString = Base64.decode(currUser.getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            logo.setImageBitmap(decodedByte);
        }

        List<String> skills = new ArrayList<>();
        skills.add("JavaScript");
        skills.add("Django");
        skills.add("React");
        skills.add("C++");
        skills.add("JAVA");
        skills.add("Kotlin");
        skills.add("Python");
        skills.add("Machine Learning");
        skills.add("Digital Marketing");
        skills.add("UI Design");

        String skillstxt = "";
        String curruserskills = currUser.getSkills();
        for(int i = 0; i < curruserskills.length(); i++){
            if(curruserskills.charAt(i) != ','){
                skillstxt += String.valueOf(skills.get(curruserskills.charAt(i) - '0'));
                skillstxt += ", ";
            }
        }

        skills1.setText(skillstxt);

    }

    private void init() {
        logout = findViewById(R.id.logout);
        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);
        skills1 = findViewById(R.id.skills);
        connections = findViewById(R.id.connections);
        accepted = findViewById(R.id.accepted);
        rejected = findViewById(R.id.rejected);
    }


}