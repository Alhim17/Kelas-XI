package com.komputerkit.helloword;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvHasil;
    private EditText etBil_1, etBil_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen UI
        tvHasil = findViewById(R.id.tv_hasil);
        etBil_1 = findViewById(R.id.et_bil_1);
        etBil_2 = findViewById(R.id.et_bil_2);
    }

    // Method untuk tombol Penjumlahan
    public void btnJumlah(View v) {
        if (isInputKosong()) return;
        double bil_1 = Double.parseDouble(etBil_1.getText().toString());
        double bil_2 = Double.parseDouble(etBil_2.getText().toString());
        double hasil = bil_1 + bil_2;
        tvHasil.setText(formatHasil(hasil));
    }

    // Method untuk tombol Pengurangan
    public void btnKurang(View v) {
        if (isInputKosong()) return;
        double bil_1 = Double.parseDouble(etBil_1.getText().toString());
        double bil_2 = Double.parseDouble(etBil_2.getText().toString());
        double hasil = bil_1 - bil_2;
        tvHasil.setText(formatHasil(hasil));
    }

    // Method untuk tombol Perkalian
    public void btnKali(View v) {
        if (isInputKosong()) return;
        double bil_1 = Double.parseDouble(etBil_1.getText().toString());
        double bil_2 = Double.parseDouble(etBil_2.getText().toString());
        double hasil = bil_1 * bil_2;
        tvHasil.setText(formatHasil(hasil));
    }

    // Method untuk tombol Pembagian
    public void btnBagi(View v) {
        if (isInputKosong()) return;
        double bil_1 = Double.parseDouble(etBil_1.getText().toString());
        double bil_2 = Double.parseDouble(etBil_2.getText().toString());
        if (bil_2 == 0) {
            tvHasil.setText("Tidak bisa dibagi 0");
        } else {
            double hasil = bil_1 / bil_2;
            tvHasil.setText(formatHasil(hasil));
        }
    }

    // Cek input kosong dan tampilkan Toast jika ada yang kosong
    private boolean isInputKosong() {
        String sBil_1 = etBil_1.getText().toString().trim();
        String sBil_2 = etBil_2.getText().toString().trim();
        if (sBil_1.isEmpty() || sBil_2.isEmpty()) {
            Toast.makeText(this, "Ada Bilangan Yang Kosong", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    // Helper untuk format hasil (tanpa .0 jika bilangan bulat)
    private String formatHasil(double val) {
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return String.format("%s", val);
        }
    }
}