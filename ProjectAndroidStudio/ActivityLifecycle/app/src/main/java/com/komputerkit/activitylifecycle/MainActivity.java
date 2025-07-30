package com.komputerkit.activitylifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNamaBarang = findViewById(R.id.etNamaBarang);
    }

    public void goToBarangActivity(View view) {
        String namaBarang = etNamaBarang.getText().toString();
        Intent intent = new Intent(MainActivity.this, BarangActivity.class);
        intent.putExtra("NAMA_BARANG", namaBarang);
        startActivity(intent);
    }
}