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

public class Succes_register extends AppCompatActivity {

    Animation splash, btt, ttb;
    Button btn_explore;
    ImageView icon_success;
    TextView app_title, app_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_register);

        btn_explore = findViewById(R.id.btn_explore);
        icon_success = findViewById(R.id.icon_success);
        app_title = findViewById(R.id.app_title);
        app_subtitle = findViewById(R.id.app_subtitle);


        //load Animation
        splash = AnimationUtils.loadAnimation(this, R.anim.splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        //Run Animation
        btn_explore.startAnimation(btt);
        icon_success.startAnimation(splash);
        app_subtitle.startAnimation(ttb);
        app_subtitle.startAnimation(ttb);


        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnsuccess = new Intent(Succes_register.this ,HomeActivity.class);
                startActivity(btnsuccess);
            }
        });
    }
}
