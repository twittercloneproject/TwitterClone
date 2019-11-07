package com.example.twitterclone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    private RegisterPresenter presenter;
    private Button registerButton;
    private Button uploadPicture;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private WebView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Register activity", "onCreate() called");
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter();

        profilePic = (WebView) findViewById(R.id.ProfilePictureWebView);
        uploadPicture = (Button) findViewById(R.id.EditProfilePicture);
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        registerButton = (Button) findViewById(R.id.SignUpButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
                final String firstName = firstNameEditText.getText().toString();
                lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
                final String lastName = lastNameEditText.getText().toString();
                usernameEditText = (EditText) findViewById(R.id.usernameEditText);
                final String username = usernameEditText.getText().toString();
                passwordEditText = (EditText) findViewById(R.id.passwordEditText);
                final String password = passwordEditText.getText().toString();
                presenter.register(firstName, lastName, username, password, "", RegisterActivity.this);
            }

        });

    }


    public void onRegister(boolean success) {
        if(success) {
            Intent i = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
