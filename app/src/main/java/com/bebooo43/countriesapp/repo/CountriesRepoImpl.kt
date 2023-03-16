package com.bebooo43.countriesapp.repo

import com.bebooo43.countriesapp.repo.remote.RetrofitService
import com.bebooo43.countriesapp.repo.remote.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CountriesRepoImpl(
    private val retrofit: RetrofitService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CountriesRepo {

    override suspend fun getAllCountries() =
        safeApiCall(dispatcher) { retrofit.getAllCountries() }

}