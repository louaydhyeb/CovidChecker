package com.ldcoding.covidstatcheckerapp.data.remote

import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoronavirusApi {

    @GET("/v3/covid-19/countries")
    suspend fun getCountries(): List<CountriesDto>

    @GET("/v3/covid-19/countries/{countryName}")
    suspend fun getCountryDetails(@Path("countryName") countryName: String): CountriesDto


}