package com.dzu.tiketsaya1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TicketDetailActivity extends AppCompatActivity {

    Button btn_buyticket;
    TextView titleticket,locationticket, photosticket, wifiticket, festivalticket, shortdesk;
    ImageView header_ticket;
    LinearLayout btn_back;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        titleticket = findViewById(R.id.titleticket);
        locationticket = findViewById(R.id.locationticket);
        photosticket = findViewById(R.id.photosticket);
        wifiticket = findViewById(R.id.wifiticket);
        festivalticket = findViewById(R.id.festivalticket);
        shortdesk = findViewById(R.id.shortdesk);

        header_ticket = findViewById(R.id.header_ticket);
        btn_back = findViewById(R.id.btn_back);
        btn_buyticket = findViewById(R.id.btn_buyticket);


        //ambil data dari Intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("jenis_ticket");

        //ambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("WIsata").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titleticket.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                locationticket.setText(dataSnapshot.child("lokasi").getValue().toString());
                photosticket.setText(dataSnapshot.child("is_photo_spot").getValue().toString());
                wifiticket.setText(dataSnapshot.child("is_wifi").getValue().toString());
                festivalticket.setText(dataSnapshot.child("is_festival").getValue().toString());
                shortdesk.setText(dataSnapshot.child("shor_desc").getValue().toString());

                Picasso.with(TicketDetailActivity.this).load(dataSnapshot.child("url_thumbnail").getValue().toString()).centerCrop().fit().into(header_ticket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_buyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gocheckout = new Intent(TicketDetailActivity.this , TicketCheckoutActivity.class);
                gocheckout.putExtra("jenis_ticket", jenis_tiket_baru);
                startActivity(gocheckout);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
