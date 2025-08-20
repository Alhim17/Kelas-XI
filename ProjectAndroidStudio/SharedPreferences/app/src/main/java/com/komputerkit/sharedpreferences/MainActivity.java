package com.komputerkit.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaBarang, etStokBarang;
    private Button btnSimpan, btnTampil;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "barang";
    private static final String KEY_NAMA = "KEY_NAMA";
    private static final String KEY_STOK = "KEY_STOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaBarang = findViewById(R.id.et_nama_barang);
        etStokBarang = findViewById(R.id.et_stok_barang);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnTampil = findViewById(R.id.btn_tampil);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNamaBarang.getText().toString().trim();
                String stokStr = etStokBarang.getText().toString().trim();

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(stokStr)) {
                    Toast.makeText(MainActivity.this, "Nama dan Stok tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                float stok;
                try {
                    stok = Float.parseFloat(stokStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Stok harus berupa angka", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAMA, nama);
                editor.putFloat(KEY_STOK, stok);
                editor.apply();

                Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = sharedPreferences.getString(KEY_NAMA, "");
                float stok = sharedPreferences.getFloat(KEY_STOK, 0f);

                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(MainActivity.this, "Data belum disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    etNamaBarang.setText(nama);
                    etStokBarang.setText(String.valueOf(stok));
                    Toast.makeText(MainActivity.this, "Data berhasil dimuat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}