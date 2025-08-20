package com.komputerkit.mysqldatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Data class untuk item
data class Item(val name: String)

// Adapter RecyclerView untuk daftar item
class ItemAdapter(private val context: Context, private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.tvItemName.text = item.name
        // Listener untuk ikon tiga titik
        holder.btnOptions.setOnClickListener { v ->
            showPopupMenu(v, position)
        }
    }

    override fun getItemCount(): Int = items.size

    // Menampilkan PopupMenu saat ikon diklik
    private fun showPopupMenu(view: View, position: Int) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.item_options_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_edit -> {
                    Toast.makeText(context, "Tombol Edit diklik", Toast.LENGTH_SHORT).show()
                    // TODO: Implement edit functionality
                    true
                }
                R.id.menu_delete -> {
                    Toast.makeText(context, "Tombol Delete diklik", Toast.LENGTH_SHORT).show()
                    // TODO: Implement delete functionality
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    // ViewHolder untuk item
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        val btnOptions: ImageView = itemView.findViewById(R.id.btnOptions)
    }
}
