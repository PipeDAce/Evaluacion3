package com.example.evaluacion3.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacion3.Models.Dispositivo
import com.example.evaluacion3.R

class AdapterDispositivo (private val dispositivos: ArrayList<Dispositivo>):
RecyclerView.Adapter<AdapterDispositivo.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre : TextView = itemView.findViewById(R.id.rvDispositivos)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispositivos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dispositivos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dispositivo = dispositivos[position]

        holder.nombre.text = dispositivo.nombre
    }

}