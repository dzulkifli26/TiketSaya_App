package com.dzu.tiketsaya1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.MyViewHolder> {

    Context context;
    ArrayList<Myticket> myticket;

    public  TiketAdapter(Context c, ArrayList<Myticket> p) {
        context = c;
        myticket = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myticket,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xnama_wisata.setText(myticket.get(i).getNama_wisata());
        myViewHolder.xjumlah_tiket.setText(myticket.get(i).getJumlah_tiket() + " Tickets");
        myViewHolder.xlokasi.setText(myticket.get(i).getLokasi());

        final String getNamaWisata = myticket.get(i).getNama_wisata();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototiketdetails = new Intent(context, TicketDetailActivity.class);
                gototiketdetails.putExtra("nama_wisata", getNamaWisata);
                context.startActivity(gototiketdetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myticket.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

         TextView xnama_wisata, xlokasi, xjumlah_tiket;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            xnama_wisata = itemView.findViewById(R.id.xnama_wisata);
            xlokasi = itemView.findViewById(R.id.xlokasi);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_tiket);
        }
    }
}
