package com.komputerkit.mysqldatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    private ArrayList<Barang> listBarang;

    public BarangAdapter(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        Barang barang = listBarang.get(position);
        holder.tvNamaBarang.setText(barang.getNamaBarang());
        holder.tvStok.setText("Stok: " + barang.getStok());
        holder.tvHarga.setText("Harga: " + barang.getHarga());
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public void setListBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
        notifyDataSetChanged();
    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvStok, tvHarga;
        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            tvStok = itemView.findViewById(R.id.tvStok);
            tvHarga = itemView.findViewById(R.id.tvHarga);
        }
    }
}
