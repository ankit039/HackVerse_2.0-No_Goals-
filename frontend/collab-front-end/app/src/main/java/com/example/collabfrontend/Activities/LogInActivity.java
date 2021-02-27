package com.example.collabfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.LoginUserPost;
import com.example.collabfrontend.model.LoginUserGet;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    Button signin;
    ImageButton signup;
    EditText username, password;
    ProgressBar progressBar;
    String usernametxt;
    String passwordtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        Paper.init(this);

        //check for existing user
        LoginUserGet currUser2 = Paper.book().read("currUser", null);
        if(currUser2 != null){
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signin.setVisibility(View.INVISIBLE);

                try {
                    usernametxt = username.getText().toString();
                    passwordtxt = password.getText().toString();

                    if(usernametxt.isEmpty() || passwordtxt.isEmpty()){
                        progressBar.setVisibility(View.INVISIBLE);
                        signin.setVisibility(View.VISIBLE);
                        Toast.makeText(LogInActivity.this, "Please Verify All field", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    progressBar.setVisibility(View.INVISIBLE);
                    signin.setVisibility(View.VISIBLE);
                    Log.e("Exception", String.valueOf(e));
                    Toast.makeText(LogInActivity.this, "Please enter details in correct format!", Toast.LENGTH_SHORT).show();
                }

                LogIn();
            }
        });

    }

    private void LogIn() {
        LoginUserPost curr = new LoginUserPost(usernametxt, passwordtxt);
        Call<LoginUserGet> call = RetrofitClient.getInstance().getMyApi().loginUser(curr);
        call.enqueue(new Callback<LoginUserGet>() {
            @Override
            public void onResponse(Call<LoginUserGet> call, Response<LoginUserGet> response) {
                LoginUserGet result_got = response.body();
                if(result_got.isSucess()){
                    Toast.makeText(LogInActivity.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                    Paper.book().write("currUser", result_got);
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else if(!result_got.isSucess()){
                    Toast.makeText(LogInActivity.this, "User not Registered!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LogInActivity.this, "Some Error Occurred!!", Toast.LENGTH_SHORT).show();
                }

                Log.e("this", result_got.toString());
            }

            @Override
            public void onFailure(Call<LoginUserGet> call, Throwable t) {
                Log.e("this", "Failure" + t.getMessage());
                Toast.makeText(LogInActivity.this, "Some error Occurred!!", Toast.LENGTH_SHORT).show();
            }
        });

        progressBar.setVisibility(View.INVISIBLE);
        signin.setVisibility(View.VISIBLE);

    }

    private void init() {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.cpd)); //status bar or the time bar at the top

        signin = findViewById(R.id.sign_in);
        signup = findViewById(R.id.sign_up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress_circular);
    }
}