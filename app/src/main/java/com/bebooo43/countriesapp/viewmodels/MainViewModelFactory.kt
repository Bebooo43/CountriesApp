package com.bebooo43.countriesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bebooo43.countriesapp.repo.CountriesRepoImpl
import com.bebooo43.countriesapp.repo.remote.RetrofitService

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        MainViewModel::class.java -> MainViewModel(CountriesRepoImpl(RetrofitService.getInstance())) as T
        else -> throw java.lang.IllegalArgumentException("Cannot Create instance of ${modelClass.name}")
    }
}