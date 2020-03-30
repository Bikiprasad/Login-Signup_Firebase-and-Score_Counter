package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private TextView mCreateNewaccount;
    private TextView mForgetPassword;
    private FirebaseAuth mAuth;
    private Button mLoginBtn;
    private EditText mEmail;
    private EditText mPassword;
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mProgress = findViewById(R.id.progressBar);
        mLoginBtn=(Button) findViewById(R.id.loginbutton);
        mEmail =(EditText) findViewById(R.id.emailTextlogin);
        mPassword =(EditText) findViewById(R.id.passwordTextlogin);
        mForgetPassword = (TextView) findViewById(R.id.forgetpasswordtextloginpage);
        mCreateNewaccount = (TextView) findViewById(R.id.singuptextloginpage);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            SendtoMainActivity();
            finish();
        }

        mCreateNewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToRegisterActivity();
            }
        });
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToForgetPasswordActivity();
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Enter registered Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                mProgress.setVisibility(View.VISIBLE);
                //Login Authentication

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            SendtoMainActivity();
                        }
                        else{
                            Toast.makeText(login.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

    }


    private void SendToRegisterActivity()
    {
        Intent RegisterIntent = new Intent(login.this,signup.class);
        startActivity(RegisterIntent);
        finish();
    }
    private void SendtoMainActivity()
    {
        Intent MainIntent = new Intent(login.this,MainActivity.class);
        startActivity(MainIntent);
        finish();
    }
    private void SendToForgetPasswordActivity()
    {
        Intent ForgetPasswordIntent = new Intent(login.this,forgetpassword.class);
        startActivity(ForgetPasswordIntent);
        finish();
    }
}
