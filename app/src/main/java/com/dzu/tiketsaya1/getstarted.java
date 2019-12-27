package com.dzu.tiketsaya1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class getstarted extends AppCompatActivity {

    Button btn_sign_in , btn_signin_home;
    Animation ttb, btt;
    ImageView logo_getstarted;
    TextView text_getstarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_signin_home = findViewById(R.id.btn_signin_home);

        logo_getstarted = findViewById(R.id.logo_getstarted);
        text_getstarted = findViewById(R.id.text_getstarted);

        logo_getstarted.startAnimation(ttb);
        text_getstarted.startAnimation(ttb);
        btn_sign_in.startAnimation(btt);
        btn_signin_home.startAnimation(btt);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosignin = new Intent(getstarted.this,Sign_In.class);
                startActivity(gosignin);
            }
        });
        btn_signin_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosigninnew = new Intent(getstarted.this, Register.class);
                startActivity(gosigninnew);
            }
        });
    }
}
