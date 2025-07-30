// Adapter untuk RecyclerView daftar siswa
// Menghubungkan data model Siswa ke item_siswa.xml
package com.komputerkit.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder> {
    private List<Siswa> daftarSiswa;
    private Context context;

    // Konstruktor Adapter
    public SiswaAdapter(List<Siswa> daftarSiswa, Context context) {
        this.daftarSiswa = daftarSiswa;
        this.context = context;
    }

    // ViewHolder untuk item siswa
    public static class SiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaSiswa, tvAlamatSiswa, tvMenuOptions;
        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi komponen tampilan
            tvNamaSiswa = itemView.findViewById(R.id.tvNamaSiswa);
            tvAlamatSiswa = itemView.findViewById(R.id.tvAlamatSiswa);
            tvMenuOptions = itemView.findViewById(R.id.tv_menu_options);
        }
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_siswa.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        // Ambil data siswa berdasarkan posisi
        Siswa siswa = daftarSiswa.get(position);
        // Set data ke tampilan
        holder.tvNamaSiswa.setText(siswa.getNama());
        holder.tvAlamatSiswa.setText(siswa.getAlamat());

        // Listener klik pada itemView (tampilkan data dengan Toast)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan = "Nama: " + siswa.getNama() + "\nAlamat: " + siswa.getAlamat();
                Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
            }
        });

        // Listener klik pada titik tiga (menu opsi)
        holder.tvMenuOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.tvMenuOptions);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_simpan) {
                        Toast.makeText(context, "Simpan Data " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                        return true;
                    } else if (itemId == R.id.menu_hapus) {
                        int pos = holder.getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            String nama = daftarSiswa.get(pos).getNama();
                            daftarSiswa.remove(pos);
                            notifyItemRemoved(pos);
                            Toast.makeText(context, nama + " Sudah di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                    return false;
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // Jumlah item dalam daftar
        return daftarSiswa.size();
    }
}
