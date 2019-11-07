package com.example.twitterclone.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.api.model.SignInRequest;
import com.example.twitterclone.api.model.SignInResult;
import com.example.twitterclone.presenter.SignInPresenter;

public class SignInActivity extends AppCompatActivity {

    private Button signInButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private SignInPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SignIn activity", "onCreate() called");
        setContentView(R.layout.activity_sign_in);
        presenter = new SignInPresenter();

        signInButton = (Button) findViewById(R.id.SignUpButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText = (EditText) findViewById(R.id.usernameEditText);
                final String username = usernameEditText.getText().toString();
                passwordEditText = (EditText) findViewById(R.id.passwordEditText);
                final String password = passwordEditText.getText().toString();
                presenter.login(username, password, SignInActivity.this);
            }

        });
    }

    public void onSignIn(boolean success) {
        if(success) {
            Intent i = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(SignInActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
        }
    }
}
