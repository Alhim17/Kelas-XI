// Model data untuk Siswa
// Menyimpan nama dan alamat siswa
package com.komputerkit.recyclerview;

public class Siswa {
    private String nama;
    private String alamat;

    // Konstruktor
    public Siswa(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Getter untuk alamat
    public String getAlamat() {
        return alamat;
    }
}
