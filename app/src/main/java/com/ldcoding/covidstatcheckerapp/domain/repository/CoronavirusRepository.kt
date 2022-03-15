package com.ldcoding.covidstatcheckerapp.domain.repository

import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto

interface CoronavirusRepository {

    suspend fun getCountries(): List<CountriesDto>

    suspend fun getCountry(countryName: String): CountriesDto
}