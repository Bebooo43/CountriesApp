package com.bebooo43.countriesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bebooo43.countriesapp.databinding.ListItemBinding
import com.bebooo43.countriesapp.models.CountryUiModel

class CountriesAdapter(private val list: List<CountryUiModel>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        fun bind(countryItem: CountryUiModel) {
            listItemBinding.also {
                it.titleTv.text = countryItem.title
                it.codeTv.text = countryItem.code
                it.descTv.text = countryItem.desc
            }
        }

    }

}