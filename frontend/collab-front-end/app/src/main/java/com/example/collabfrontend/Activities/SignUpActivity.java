package com.example.collabfrontend.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.collabfrontend.Interface.RetrofitClient;
import com.example.collabfrontend.R;
import com.example.collabfrontend.model.SignUpGet;
import com.example.collabfrontend.model.SignUpPost;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    RelativeLayout step_1, step_2;
    ImageButton signup, signup2, login;
    EditText name, email, username, password1, password2;
    ProgressBar progressBar;
    Uri pickedImgUri;
    ImageView addpic;
    CheckBox skills_0, skills_1, skills_2, skills_3, skills_4, skills_5, skills_6, skills_7, skills_8, skills_9;
    String emailtxt = "", nametxt = "", usernametxt = "", password1txt = "", password2txt = "", imagetxt = "", skillstxt = "",
            connecttxt = "", accepttxt = "", rejecttxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Init();

        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(SignUpActivity.this);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    emailtxt = email.getText().toString().trim();
                    password1txt = password1.getText().toString();
                    password2txt = password2.getText().toString();
                    nametxt = name.getText().toString().trim();
                    usernametxt = username.getText().toString();

                    if (emailtxt.isEmpty() || nametxt.isEmpty() || password1txt.length() < 8 || password1txt.isEmpty() ||
                            !password1txt.equals(password2txt) || usernametxt.isEmpty() || pickedImgUri == null
                            || usernametxt.indexOf('@') != -1) {
                        if (pickedImgUri == null) {
                            Toast.makeText(SignUpActivity.this, "Select a Profile pic.", Toast.LENGTH_SHORT).show();
                        } else if (password1txt.length() < 8) {
                            Toast.makeText(SignUpActivity.this, "Password should be of length 8 or more", Toast.LENGTH_SHORT).show();
                        } else if (usernametxt.indexOf('@') != -1) {
                            Toast.makeText(SignUpActivity.this, "Username should not contain @", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Please Verify all fields", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //everything is ok and fields are filled now we can go to next step
                        step_1.setVisibility(View.GONE);
                        step_2.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Toast.makeText(SignUpActivity.this, "Please enter details in correct format!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup2.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                skillstxt = "";
                try{
                    if(skills_0.isChecked()){
                        skillstxt += ",0";
                    }

                    if(skills_1.isChecked()){
                        skillstxt += ",1";
                    }

                    if(skills_2.isChecked()){
                        skillstxt += ",2";
                    }

                    if(skills_3.isChecked()){
                        skillstxt += ",3";
                    }

                    if(skills_4.isChecked()){
                        skillstxt += ",4";
                    }

                    if(skills_5.isChecked()){
                        skillstxt += ",5";
                    }

                    if(skills_6.isChecked()){
                        skillstxt += ",6";
                    }

                    if(skills_7.isChecked()){
                        skillstxt += ",7";
                    }

                    if(skills_8.isChecked()){
                        skillstxt += ",8";
                    }

                    if(skills_9.isChecked()){
                        skillstxt += ",9";
                    }

                    if(skillstxt.charAt(0) == ','){
                        skillstxt = skillstxt.substring(1, skillstxt.length());
                    }

                    Log.e("this", skillstxt);

                    Toast.makeText(SignUpActivity.this, skillstxt, Toast.LENGTH_SHORT).show();
                    signingUp();

                }catch(Exception e){
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void signingUp() {
        SignUpPost curr = new SignUpPost(usernametxt, nametxt, emailtxt, password1txt, imagetxt, skillstxt, connecttxt, accepttxt, rejecttxt);
        Call<SignUpGet> call = RetrofitClient.getInstance().getMyApi().signupUser(curr);
        call.enqueue(new Callback<SignUpGet>() {
            @Override
            public void onResponse(Call<SignUpGet> call, Response<SignUpGet> response) {
                Log.e("this", response.body().toString());
                if(response.body().isSucess()){
                    Toast.makeText(SignUpActivity.this, "Registered Successfully, Please LogIn!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpActivity.this, "Registeration Failed, Please LogIn Again!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SignUpGet> call, Throwable t) {
                Log.e("this", "failure"+t.getMessage());
                Toast.makeText(SignUpActivity.this, "Registeration Failed, Please LogIn Again!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                pickedImgUri = result.getUri();
                addpic.setImageURI(pickedImgUri);

                final InputStream imageStream;
                String encodedImage = "";
                try {
                    imageStream = getContentResolver().openInputStream(pickedImgUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    encodedImage = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imagetxt = encodedImage;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.e("this", String.valueOf(error));
            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void Init() {
        step_1 = findViewById(R.id.step_1);
        step_2 = findViewById(R.id.step_2);
        step_1.setVisibility(View.VISIBLE);
        step_2.setVisibility(View.GONE);

        signup = findViewById(R.id.sign_up);
        signup2 = findViewById(R.id.sign_up2);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.progress_circular);
        addpic = findViewById(R.id.add_pic);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password1 = findViewById(R.id.pswd1);
        password2 = findViewById(R.id.pswd2);

        skills_0 = findViewById(R.id.skill_0);
        skills_1 = findViewById(R.id.skill_1);
        skills_2 = findViewById(R.id.skill_2);
        skills_3 = findViewById(R.id.skill_3);
        skills_4 = findViewById(R.id.skill_4);
        skills_5 = findViewById(R.id.skill_5);
        skills_6 = findViewById(R.id.skill_6);
        skills_7 = findViewById(R.id.skill_7);
        skills_8 = findViewById(R.id.skill_8);
        skills_9 = findViewById(R.id.skill_9);
    }
}