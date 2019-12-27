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

public class SuccesActivity extends AppCompatActivity {

    Animation splash, ttb, btt;
    Button btn_viewticket,btn_mydashboard ;
    TextView text_title, text_subtitle ;
    ImageView icon_success_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);

        btn_viewticket = findViewById(R.id.btn_viewticket);
        btn_mydashboard = findViewById(R.id.btn_mydashboard);
        text_title = findViewById(R.id.text_title);
        text_subtitle = findViewById(R.id.text_subtitle);
        icon_success_ticket = findViewById(R.id.icon_success_ticket);

        splash = AnimationUtils.loadAnimation(this, R.anim.splash);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        btn_viewticket.startAnimation(btt);
        btn_viewticket.startAnimation(btt);

        text_title.startAnimation(ttb);
        text_subtitle.startAnimation(ttb);

        icon_success_ticket.startAnimation(splash);

        btn_mydashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodashboard = new Intent(SuccesActivity.this , HomeActivity.class);
                startActivity(gotodashboard);
            }
        });

        btn_viewticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetail = new Intent(SuccesActivity.this , ProfileActivity.class);
                startActivity(gotodetail);
            }
        });
    }
}
