package com.ldcoding.covidstatcheckerapp.domain.model

data class Country(
    val cases: Int,
    val country: String,
    val deaths: Int,
    val recovered: Int,
)
