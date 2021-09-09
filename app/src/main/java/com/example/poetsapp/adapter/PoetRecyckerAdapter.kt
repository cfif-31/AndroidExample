package com.example.poetsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poetsapp.R
import com.example.poetsapp.types.Poet
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PoetRecyckerAdapter(private val poets: List<Poet>):RecyclerView.Adapter<PoetRecyckerAdapter.PoetViewHolder>() {
    class PoetViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView){

        val PoetFioField: TextView = ItemView.findViewById(R.id.fioId);
        val DateField: TextView = ItemView.findViewById(R.id.dateId);
        val DescriptionField: TextView = ItemView.findViewById(R.id.descriptionId);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetViewHolder {
        val ItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PoetViewHolder(ItemView)
    }
    val dateF:DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    override fun onBindViewHolder(holder: PoetViewHolder, position: Int) {

        holder.PoetFioField.text = listOf(poets[position].firstName,
            poets[position].lastName, poets[position].middleName).joinToString(" ")
        holder.DateField.text = listOf(dateF.format(poets[position].birthDate!!), dateF.format(poets[position].deadDate!!)).joinToString(" - ");
        holder.DescriptionField.text = poets[position].description;
    }

    override fun getItemCount(): Int {
        return poets.count()
    }
}