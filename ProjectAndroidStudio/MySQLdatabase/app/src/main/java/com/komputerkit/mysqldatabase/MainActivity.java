
package com.komputerkit.mysqldatabase;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.database.Cursor;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText etBarang, etStok, etHarga;
    private RecyclerView recyclerView;
    private BarangAdapter barangAdapter;
    private ArrayList<Barang> listBarang;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        etHarga = findViewById(R.id.etHarga);
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listBarang = new ArrayList<>();
        barangAdapter = new BarangAdapter(listBarang);
        recyclerView.setAdapter(barangAdapter);

        loadData();

        fabAdd.setOnClickListener(v -> tambahBarangBaru());
    }

    private void tambahBarangBaru() {
        String nama = etBarang.getText().toString().trim();
        String stokStr = etStok.getText().toString().trim();
        String hargaStr = etHarga.getText().toString().trim();

        if (nama.isEmpty() || stokStr.isEmpty() || hargaStr.isEmpty()) {
            Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        int stok = Integer.parseInt(stokStr);
        double harga = Double.parseDouble(hargaStr);
        boolean inserted = dbHelper.tambahBarang(nama, stok, harga);
        if (inserted) {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            kosongkanField();
            loadData();
        } else {
            Toast.makeText(this, "Terjadi kesalahan. Data gagal disimpan.", Toast.LENGTH_SHORT).show();
        }
    }

    private void kosongkanField() {
        etBarang.setText("");
        etStok.setText("");
        etHarga.setText("");
    }

    private void loadData() {
        listBarang.clear();
        Cursor cursor = null;
        try {
            cursor = dbHelper.lihatSemuaBarang();
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));
                    String nama = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAMA));
                    int stok = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_STOK));
                    double harga = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_HARGA));
                    listBarang.add(new Barang(id, nama, stok, harga));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Gagal memuat data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) cursor.close();
        }
        barangAdapter.setListBarang(listBarang);
    }
}