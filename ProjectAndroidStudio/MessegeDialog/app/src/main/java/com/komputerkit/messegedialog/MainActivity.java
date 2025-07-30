package com.komputerkit.messegedialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnToast, btnAlert, btnAlertDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnToast);
        btnAlert = findViewById(R.id.btnAlert);
        btnAlertDialogButton = findViewById(R.id.btnAlertDialogButton);

        btnToast.setOnClickListener(v -> showToast("Ini adalah pesan Toast!"));
        btnAlert.setOnClickListener(v -> showAlert("Pemberitahuan", "Selamat, Anda berhasil memunculkan alert!"));
        btnAlertDialogButton.setOnClickListener(v -> showAlertDialogWithOptions());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showAlertDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("YA", (dialog, which) -> showToast("Anda memilih YA"))
                .setNegativeButton("TIDAK", (dialog, which) -> showToast("Anda memilih TIDAK"))
                .show();
    }
}