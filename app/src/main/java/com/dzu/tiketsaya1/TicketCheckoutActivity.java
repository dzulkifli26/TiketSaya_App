package com.dzu.tiketsaya1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import java.util.Random;

public class TicketCheckoutActivity extends AppCompatActivity {

    Button btn_buyticket, btnminus, btnplus;
    LinearLayout btn_back;
    TextView jumlahtiket, mybalance, price, nama_wisata, lokasi, ketentuan;
    Integer valueticket = 1;
    Integer mybalance1 = 0;
    Integer valueprice = 0;
    Integer valuemultiple = 0;
    ImageView notice_uang;
    Integer sisabalance = 0;

    DatabaseReference  reference, reference2, reference3, reference4;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    String date_wisata = "";
    String time_wisata = "";

    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);

        getUsernameLocal();

        //mengambil data dari Intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("jenis_ticket");

        btnminus = findViewById(R.id.btnminus);
        btn_back = findViewById(R.id.btn_back);
        btnplus = findViewById(R.id.btnplus);
        jumlahtiket = findViewById(R.id.jumlahtiket);
        mybalance = findViewById(R.id.mybalance);
        price = findViewById(R.id.price);
        btn_buyticket = findViewById(R.id.btn_buyticket);
        notice_uang = findViewById(R.id.notice_uang);
        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        ketentuan = findViewById(R.id.ketentuan);

        //Declare new Value
        jumlahtiket.setText(valueticket.toString());
        mybalance.setText("US$" +mybalance1 + "");
        valueprice = valuemultiple * valueticket;


        //Default button ticket minus hilang
        btnminus.animate().alpha(0).setDuration(300).start();
        btnminus.setEnabled(false);
        notice_uang.setVisibility(View.GONE);

        //mengambil data dari Firebase beda Tabel Induk
        reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mybalance1 = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                mybalance.setText("US$" +mybalance1 + "");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //mengambil data dari Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("WIsata").child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();

                valuemultiple = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());


                valueprice = valuemultiple * valueticket;
                price .setText("US$" + valueprice + "");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueticket+=1;
                jumlahtiket.setText(valueticket.toString());
                if (valueticket > 1) {
                    btnminus.animate().alpha(1).setDuration(300).start();
                    btnminus.setEnabled(true);
                }
                valueprice = valuemultiple * valueticket;
                price .setText("US$" + valueprice + "");
                if (valueprice > mybalance1){
                    btn_buyticket.animate().translationY(250).alpha(0).setDuration(300).start();
                    btn_buyticket.setEnabled(false);
                    mybalance.setTextColor(Color.parseColor("#D1206B"));
                    notice_uang.setVisibility(View.VISIBLE);
                }
            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueticket-=1;
                jumlahtiket.setText(valueticket.toString());
                if (valueticket <2 ) {
                    btnminus.animate().alpha(0).setDuration(300).start();
                    btnminus.setEnabled(false);
                }
                valueprice = valuemultiple * valueticket;
                price .setText("US$" + valueprice + "");
                if(valueprice <= mybalance1) {
                    btn_buyticket.animate().translationY(0).alpha(1).setDuration(300).start();
                    btn_buyticket.setEnabled(true);
                    mybalance.setTextColor(Color.parseColor("#203DD1"));
                    notice_uang.setVisibility(View.GONE);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_buyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //menyimpan data ticket ke Firebase
                reference3 = FirebaseDatabase.getInstance().getReference().child("MyTicket").child(username_key_new).child(nama_wisata.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_ticket").setValue(nama_wisata.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama_wisata").setValue(nama_wisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(lokasi.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference3.getRef().child("jumlah_tiket").setValue(valueticket.toString());
                        reference3.getRef().child("date_wisata").setValue(date_wisata);
                        reference3.getRef().child("time_wisata").setValue(time_wisata);

                        Intent gotosuccess = new Intent(TicketCheckoutActivity.this , SuccesActivity.class);
                        startActivity(gotosuccess);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                // update balance user
                reference4 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sisabalance = mybalance1 - valueprice;
                        reference4.getRef().child("user_balance").setValue(sisabalance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
