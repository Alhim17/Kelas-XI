package com.komputerkit.mysqldatabase;

public class Barang {
    private int id;
    private String namaBarang;
    private int stok;
    private double harga;

    public Barang(int id, String namaBarang, int stok, double harga) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
