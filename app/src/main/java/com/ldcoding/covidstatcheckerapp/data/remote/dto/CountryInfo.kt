package com.ldcoding.covidstatcheckerapp.data.remote.dto

data class CountryInfo(
    val _id: Int,
    val flag: String,
    val iso2: String,
    val iso3: String,
    val lat: Double,
    val long: Double
)