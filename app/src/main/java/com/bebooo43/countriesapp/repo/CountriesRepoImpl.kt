package com.bebooo43.countriesapp.repo

import com.bebooo43.countriesapp.repo.remote.RetrofitService

class CountriesRepoImpl (private val retrofit: RetrofitService) : CountriesRepo {

    override suspend fun getAllCountries() = retrofit.getAllCountries()

}