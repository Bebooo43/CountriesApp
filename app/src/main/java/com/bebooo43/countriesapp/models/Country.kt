package com.bebooo43.countriesapp.models

import com.google.gson.annotations.SerializedName

data class Country(

    @field:SerializedName("capital")
    val capital: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("flag")
    val flag: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("currency")
    val currency: Currency? = null,

    @field:SerializedName("language")
    val language: Language? = null,

    @field:SerializedName("region")
    val region: String? = null,

    @field:SerializedName("demonym")
    val demonym: String? = null
) {
    fun toUiModel() =
        CountryUiModel("${name ?: ""}, ${region ?: ""}", code ?: "", capital ?: "")
}

data class CountryUiModel(val title: String, val code: String, val desc: String)

data class Currency(

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)

data class Language(

    @field:SerializedName("nativeName")
    val nativeName: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("iso639_2")
    val iso6392: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)