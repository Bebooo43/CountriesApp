package com.bebooo43.countriesapp.repo

import com.bebooo43.countriesapp.models.Country
import com.bebooo43.countriesapp.repo.remote.ResultWrapper
import retrofit2.Response


interface CountriesRepo {
    suspend fun getAllCountries(): ResultWrapper<Response<List<Country>>>
}