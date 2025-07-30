package com.komputerkit.konversisuhu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi komponen UI

        EditText editTextSuhuInput = findViewById(R.id.editTextSuhuInput);
        Spinner spinnerKonversi = findViewById(R.id.spinnerKonversi);
        Button buttonKonversi = findViewById(R.id.buttonKonversi);
        TextView textViewHasilKonversi = findViewById(R.id.textViewHasilKonversi);

        // Adapter untuk Spinner konversi
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.konversi_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKonversi.setAdapter(adapter);

        buttonKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSuhuStr = editTextSuhuInput.getText().toString().trim();
                if (inputSuhuStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                double inputSuhu;
                try {
                    inputSuhu = Double.parseDouble(inputSuhuStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pilihan = spinnerKonversi.getSelectedItem().toString();
                double hasil = 0;
                String satuan = "";
                if (pilihan.equals("Celsius ke Fahrenheit")) {
                    hasil = celsiusToFahrenheit(inputSuhu);
                    satuan = "°F";
                } else if (pilihan.equals("Celsius ke Kelvin")) {
                    hasil = celsiusToKelvin(inputSuhu);
                    satuan = "K";
                } else if (pilihan.equals("Celsius ke Reamur")) {
                    hasil = celsiusToReamur(inputSuhu);
                    satuan = "°R";
                } else if (pilihan.equals("Fahrenheit ke Celsius")) {
                    hasil = fahrenheitToCelsius(inputSuhu);
                    satuan = "°C";
                } else if (pilihan.equals("Fahrenheit ke Kelvin")) {
                    hasil = fahrenheitToKelvin(inputSuhu);
                    satuan = "K";
                } else if (pilihan.equals("Fahrenheit ke Reamur")) {
                    hasil = fahrenheitToReamur(inputSuhu);
                    satuan = "°R";
                } else if (pilihan.equals("Kelvin ke Celsius")) {
                    hasil = kelvinToCelsius(inputSuhu);
                    satuan = "°C";
                } else if (pilihan.equals("Kelvin ke Fahrenheit")) {
                    hasil = kelvinToFahrenheit(inputSuhu);
                    satuan = "°F";
                } else if (pilihan.equals("Kelvin ke Reamur")) {
                    hasil = kelvinToReamur(inputSuhu);
                    satuan = "°R";
                } else if (pilihan.equals("Reamur ke Celsius")) {
                    hasil = reamurToCelsius(inputSuhu);
                    satuan = "°C";
                } else if (pilihan.equals("Reamur ke Fahrenheit")) {
                    hasil = reamurToFahrenheit(inputSuhu);
                    satuan = "°F";
                } else if (pilihan.equals("Reamur ke Kelvin")) {
                    hasil = reamurToKelvin(inputSuhu);
                    satuan = "K";
                }
                String hasilStr = String.format("%.2f %s", hasil, satuan);
                textViewHasilKonversi.setText(hasilStr);
                // Optional: Kosongkan input setelah konversi
                // editTextSuhuInput.setText("");
                // editTextSuhuInput.requestFocus();
            }
        });

        spinnerKonversi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewHasilKonversi.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Fungsi konversi suhu
    // --- Semua fungsi konversi suhu ---
    private double celsiusToFahrenheit(double c) {
        return (c * 9.0/5.0) + 32.0;
    }
    private double celsiusToKelvin(double c) {
        return c + 273.15;
    }
    private double celsiusToReamur(double c) {
        return c * 4.0/5.0;
    }
    private double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0/9.0;
    }
    private double fahrenheitToKelvin(double f) {
        return celsiusToKelvin(fahrenheitToCelsius(f));
    }
    private double fahrenheitToReamur(double f) {
        return celsiusToReamur(fahrenheitToCelsius(f));
    }
    private double kelvinToCelsius(double k) {
        return k - 273.15;
    }
    private double kelvinToFahrenheit(double k) {
        return celsiusToFahrenheit(kelvinToCelsius(k));
    }
    private double kelvinToReamur(double k) {
        return celsiusToReamur(kelvinToCelsius(k));
    }
    private double reamurToCelsius(double r) {
        return r * 5.0/4.0;
    }
    private double reamurToFahrenheit(double r) {
        return celsiusToFahrenheit(reamurToCelsius(r));
    }
    private double reamurToKelvin(double r) {
        return celsiusToKelvin(reamurToCelsius(r));
    }
}