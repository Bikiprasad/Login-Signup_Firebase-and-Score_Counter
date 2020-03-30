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

import org.w3c.dom.Text;

public class signup extends AppCompatActivity {
    private TextView mlogintext;
    private EditText mName;
    private EditText mEmail;
    private ProgressBar mProgressBar;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mSignupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mlogintext = (TextView) findViewById(R.id.logintextsignuppage);
        mName = (EditText) findViewById(R.id.namesignupText);
        mPassword = (EditText) findViewById(R.id.passwordsignup);
        mEmail = (EditText) findViewById(R.id.emailTextsignup);
        mConfirmPassword = (EditText) findViewById(R.id.confirmpasswordsignup);
        mSignupBtn = (Button) findViewById(R.id.signupbutton);
        mProgressBar =findViewById(R.id.signupProgress);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        if (mAuth.getCurrentUser()!=null){
            SendtoMainActivity();
            finish();
        }
        mlogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendtoLoginActivity();
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= mName.getText().toString();
                String email= mEmail.getText().toString();
                String password= mPassword.getText().toString();
                String cnfpassword= mConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(name)){
                    mName.setError("Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(cnfpassword)){
                    mConfirmPassword.setError("Field is Required");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                // User Signup process here using Firebase

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signup.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            SendtoMainActivity();
                        }else{
                            Toast.makeText(signup.this, "Signup Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SendtoLoginActivity();
        finish();
    }

    private void SendtoLoginActivity(){
        Intent loginIntent = new Intent(signup.this,login.class);
        startActivity(loginIntent);
        finish();
    }

    private void SendtoMainActivity(){
        Intent mainIntent = new Intent(signup.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
