package com.dzu.tiketsaya1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout item_myticket;
    Button btn_editprofile, btn_back_home, btn_signout;
    TextView nama_lengkap , bio;
    ImageView pic_photo_register_user;

    DatabaseReference reference,reference2;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    RecyclerView place_myticket;
    ArrayList<Myticket> list;
    TiketAdapter tiketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getUsernameLocal();
        btn_back_home = findViewById(R.id.btn_back_home);
        btn_signout = findViewById(R.id.btn_signout);
        item_myticket = findViewById(R.id.item_myticket);
        btn_editprofile = findViewById(R.id.btn_editprofile);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        pic_photo_register_user = findViewById(R.id.pic_photo_register_user);
        place_myticket = findViewById(R.id.place_myticket);
        place_myticket.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Myticket>();

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                Picasso.with(ProfileActivity.this).load(dataSnapshot.child("url_photo_profile").getValue().toString()).centerCrop().fit().into(pic_photo_register_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoedit = new Intent(ProfileActivity.this, EditProfile.class);
                startActivity(gotoedit);
            }
        });

        reference2 = FirebaseDatabase.getInstance().getReference().child("MyTicket").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    Myticket p = dataSnapshot1.getValue(Myticket.class);
                    list.add(p);
                }

                tiketAdapter = new TiketAdapter(ProfileActivity.this, list);
                place_myticket.setAdapter(tiketAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, null);
                editor.apply();

                Intent gogetstarted = new Intent(ProfileActivity.this, Sign_In.class);
                startActivity(gogetstarted);
                finish();
            }
        });
    }

    public void getUsernameLocal (){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
