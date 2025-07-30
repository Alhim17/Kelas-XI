package com.komputerkit.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BarangActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        TextView tvDataBarang = findViewById(R.id.tvDataBarang);
        Intent intent = getIntent();
        String namaBarangDiterima = intent.getStringExtra("NAMA_BARANG");
        tvDataBarang.setText("Data Barang: " + namaBarangDiterima);
    }
}
