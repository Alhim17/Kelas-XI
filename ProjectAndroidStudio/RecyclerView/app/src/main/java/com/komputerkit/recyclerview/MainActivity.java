package com.komputerkit.recyclerview;


import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


// MainActivity: Menampilkan daftar siswa menggunakan RecyclerView
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SiswaAdapter adapter;
    private List<Siswa> siswaList;
    private Button btnAddSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inisialisasi Button tambah siswa
        btnAddSiswa = findViewById(R.id.btnAddSiswa);

        // 2. Inisialisasi RecyclerView dan set LayoutManager
        recyclerView = findViewById(R.id.rcvSiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Isi data awal siswa
        isiData();

        // 4. Inisialisasi Adapter dan set ke RecyclerView
        adapter = new SiswaAdapter(siswaList, this);
        recyclerView.setAdapter(adapter);

        // 5. Listener untuk tambah data siswa baru
        btnAddSiswa.setOnClickListener(v -> {
            // Data dummy baru, nama unik setiap klik
            String newName = "JONI RAMBO " + (siswaList.size() + 1);
            String newAddress = "JAKARTA";
            Siswa newSiswa = new Siswa(newName, newAddress);
            siswaList.add(newSiswa);
            // Penting: beri tahu adapter bahwa data berubah
            adapter.notifyDataSetChanged();
        });
    }

    /**
     * Fungsi untuk mengisi data siswaList dengan data dummy.
     * Dipanggil saat inisialisasi aplikasi.
     */
    private void isiData() {
        siswaList = new ArrayList<>();
        siswaList.add(new Siswa("Budi Joni", "JL. Jawa Surabaya Jawa Timur"));
        siswaList.add(new Siswa("Siti Aminah", "Jl. Sumatera No. 10, Jakarta Pusat"));
        siswaList.add(new Siswa("Agus Salim", "Jl. Kalimantan Raya 5, Bandung"));
        siswaList.add(new Siswa("Maya Dewi", "Jl. Sulawesi Barat 21, Yogyakarta"));
        siswaList.add(new Siswa("Rudi Hartono", "Jl. Irian Jaya 33, Semarang"));
        siswaList.add(new Siswa("Linda Sari", "Jl. Nusa Tenggara Timur 47, Medan"));
        siswaList.add(new Siswa("Andi Pratama", "Jl. Merdeka No. 50, Surabaya"));
        siswaList.add(new Siswa("Fatimah Azzahra", "Jl. Pahlawan No. 7, Malang"));
        siswaList.add(new Siswa("Cahya Indah", "Jl. Veteran No. 15, Bogor"));
        siswaList.add(new Siswa("Dion Saputra", "Jl. Cendrawasih No. 22, Balikpapan"));
        siswaList.add(new Siswa("Eka Lestari", "Jl. Gajah Mada No. 8, Palembang"));
    }
}