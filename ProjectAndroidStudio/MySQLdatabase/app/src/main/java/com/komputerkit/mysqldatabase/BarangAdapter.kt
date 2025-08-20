package com.komputerkit.mysqldatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter RecyclerView untuk daftar barang
class BarangAdapter(private var barangList: List<Barang>) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = barangList[position]
        holder.tvNamaBarang.text = barang.namaBarang
        holder.tvStok.text = "Stok: ${barang.stok}"
        holder.tvHarga.text = "Harga: ${barang.harga}"
    }

    override fun getItemCount(): Int = barangList.size

    // Untuk update data barang di adapter
    fun setBarangList(newList: List<Barang>) {
        barangList = newList
        notifyDataSetChanged()
    }

    // ViewHolder untuk item barang
    class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaBarang: TextView = itemView.findViewById(R.id.tvNamaBarang)
        val tvStok: TextView = itemView.findViewById(R.id.tvStok)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
    }
}
