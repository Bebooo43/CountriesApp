package com.bebooo43.countriesapp.repo

import com.bebooo43.countriesapp.models.Country
import retrofit2.Response


interface CountriesRepo {
    suspend fun getAllCountries(): Response<List<Country>>
}