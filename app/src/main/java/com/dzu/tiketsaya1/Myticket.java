package com.dzu.tiketsaya1;

public class Myticket {

    String nama_wisata, lokasi;
    String jumlah_tiket;

    public Myticket() {
    }

    public Myticket(String nama_wisata, String lokasi, String jumlah_tiket) {
        this.nama_wisata = nama_wisata;
        this.lokasi = lokasi;
        this.jumlah_tiket = jumlah_tiket;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(String jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }
}
