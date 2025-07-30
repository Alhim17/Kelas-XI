package com.komputerkit.cardview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity untuk aplikasi ContactCardViewApp.
 * Menampilkan layout activity_main yang berisi CardView daftar kontak.
 * Tidak ada logika khusus, hanya menampilkan UI.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menetapkan layout utama ke activity_main.xml
        setContentView(R.layout.activity_main);
        // Tidak perlu pengaturan EdgeToEdge atau WindowInsets untuk contoh sederhana ini.
    }
}