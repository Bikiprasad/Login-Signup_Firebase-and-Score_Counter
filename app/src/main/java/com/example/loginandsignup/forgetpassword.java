package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;



public class forgetpassword extends AppCompatActivity {

    private TextView mGotoLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mGotoLogin = (TextView) findViewById(R.id.gotologinpagefrgtpaswd);

        mGotoLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SendToLoginPage();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SendToLoginPage();
        finish();
    }
    private void SendToLoginPage(){
        Intent LoginIntent= new Intent(forgetpassword.this,login.class);
        startActivity(LoginIntent);
        finish();
    }
}
