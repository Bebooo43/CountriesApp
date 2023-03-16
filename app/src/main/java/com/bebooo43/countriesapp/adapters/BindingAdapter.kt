package com.bebooo43.countriesapp.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bebooo43.countriesapp.models.CountryUiModel


@BindingAdapter("app:bindCountriesList")
fun bindCountriesList(recyclerView: RecyclerView, list: List<CountryUiModel>?){
    list?.apply {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context)
            adapter = CountriesAdapter(list)
        }
    }
}

