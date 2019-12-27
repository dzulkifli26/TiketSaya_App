package com.dzu.tiketsaya1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    LinearLayout btnpisa, btntorri, btncandi, btnpagoda, btnmonas, btnsphinx;
    CircleView btn_toprofile;
    ImageView pic_photo_register_user;
    TextView nama_lengkap, bio, user_balance;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        pic_photo_register_user = findViewById(R.id.pic_photo_register_user);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        user_balance = findViewById(R.id.user_balance);
        btntorri = findViewById(R.id.btntorri);
        btnsphinx = findViewById(R.id.btnsphinx);
        btncandi = findViewById(R.id.btncandi);
        btnpagoda = findViewById(R.id.btnpagoda);
        btnmonas = findViewById(R.id.btnmonas);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("US$ " + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.with(HomeActivity.this).load(dataSnapshot.child("url_photo_profile").getValue().toString()).centerCrop().fit().into(pic_photo_register_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_toprofile = findViewById(R.id.btn_toprofile);
        btn_toprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goprofile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(goprofile);
            }
        });

        btnpisa = findViewById(R.id.btnpisa);
        btnpisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Pisa");
                startActivity(gopisa);
            }
        });

        btntorri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Torri");
                startActivity(gopisa);
            }
        });
        btnpagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Pagoda");
                startActivity(gopisa);
            }
        });
        btncandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Candi");
                startActivity(gopisa);
            }
        });
        btnsphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Sphinx");
                startActivity(gopisa);
            }
        });
        btnmonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopisa = new Intent(HomeActivity.this , TicketDetailActivity.class);
                gopisa.putExtra("jenis_ticket", "Monas");
                startActivity(gopisa);
            }
        });

    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
