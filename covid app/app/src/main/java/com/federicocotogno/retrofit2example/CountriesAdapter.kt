package com.federicocotogno.retrofit2example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.federicocotogno.retrofit2example.api.Country
import kotlinx.android.synthetic.main.item_country_details.view.*

class CountriesAdapter(private val onClick: (name: String) -> Unit) : RecyclerView.Adapter<CountriesAdapter.CountriesHolder>() {

    var list: List<Country> = emptyList()

    class CountriesHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(name: String, onClick: (name: String) -> Unit) {
            itemView.tv_textView.text = name
            itemView.item.setOnClickListener {
                onClick(name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country_details, parent, false);
        return CountriesHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesHolder, position: Int) {
        holder.bind(
            list[position].Country,
            onClick
        )
    }

    override fun getItemCount(): Int = list.size
}