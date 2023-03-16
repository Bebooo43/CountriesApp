package com.bebooo43.countriesapp.repo.remote

import com.bebooo43.countriesapp.BASE_URL
import com.bebooo43.countriesapp.models.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("countries.json")
    suspend fun getAllCountries(): Response<List<Country>>

    companion object {
        private var instance: RetrofitService? = null

        fun getInstance(): RetrofitService =
            if (instance != null) {
                instance!!
            } else {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().also {
                        instance = it.create(RetrofitService::class.java)
                    }
                instance!!
            }

    }

}