package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private Button mLogoutBtn;
    private TextView mtexta;
    private TextView mtextb;
    private TextView mtie;
    private Button mteama;
    private Button mclear;
    private Button mteamb;
    private Button mFinish;
    private FirebaseAuth mAuth;
    int inc=0;
    int incb=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLogoutBtn = (Button) findViewById(R.id.logoutmainbtn);
        mteama = (Button) findViewById(R.id.teama);
        mteamb = (Button) findViewById(R.id.teamb);
        mtexta = (TextView) findViewById(R.id.texta);
        mclear =(Button) findViewById(R.id.clear);
        mtextb = (TextView) findViewById(R.id.textb);
        mtie = (TextView) findViewById(R.id.tie);
        mFinish = (Button) findViewById(R.id.finish);
        mAuth = FirebaseAuth.getInstance();



        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                SendtoLoginActivity();
                finish();
            }
        });

        if (mAuth.getCurrentUser()==null){
            SendtoLoginActivity();
            finish();
        }







        mteama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFinish.isEnabled()==false) {
                    mFinish.setEnabled(true);
                    mFinish.setBackgroundResource(R.drawable.custombutton);
                    mFinish.setTextColor(Color.parseColor("#FFFFFF"));
                }
                int texta =Integer.valueOf(mtexta.getText().toString());
                inc=texta+1;
                mtexta.setText(""+inc);
                if (inc==incb){
                    mtie.setVisibility(View.VISIBLE);
                    mtie.setText("Tie Breaker!!");
                }
                else {
                    mtie.setVisibility(View.INVISIBLE);
                    mtie.setText("");
                }

            }
        });
        mteamb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFinish.isEnabled()==false) {
                    mFinish.setEnabled(true);
                    mFinish.setBackgroundResource(R.drawable.custombutton);
                    mFinish.setTextColor(Color.parseColor("#FFFFFF"));
                }
                int textb =Integer.valueOf(mtextb.getText().toString());
                incb=textb+1;
                mtextb.setText(""+incb);

                if (inc==incb){
                    mtie.setVisibility(View.VISIBLE);
                    mtie.setText("Tie Breaker!!");
                }
                else {
                    mtie.setVisibility(View.INVISIBLE);
                }
            }
        });
        mclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFinish.isEnabled()==false) {
                    mFinish.setEnabled(true);
                    mFinish.setBackgroundResource(R.drawable.custombutton);
                    mFinish.setTextColor(Color.parseColor("#FFFFFF"));
                }
                inc=0;
                incb=0;
                mtexta.setText("0");
                mtextb.setText("0");
                mtie.setVisibility(View.INVISIBLE);
            }
        });
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFinish.setEnabled(false);
                mFinish.setBackgroundResource(R.drawable.customedittext);
                mFinish.setTextColor(Color.parseColor("#000000"));
                if (inc==0 && incb ==0){
                    mtie.setVisibility(View.VISIBLE);
                    mtie.setText("No Result");
                }else {
                    if (inc>incb){
                        mtie.setVisibility(View.VISIBLE);
                        mtie.setText("Team A Wins");
                    }else if (incb>inc){
                        mtie.setVisibility(View.VISIBLE);
                        mtie.setText("Team B Wins");
                    }
                    else if (incb==inc){
                        mtie.setVisibility(View.VISIBLE);
                        mtie.setText("Match Finished\nDRAW!!");
                    }
                    else{
                        mtie.setVisibility(View.INVISIBLE);
                    }

                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void SendtoLoginActivity(){
        Intent loginIntent = new Intent(MainActivity.this,login.class);
        startActivity(loginIntent);
        finish();
    }
}
