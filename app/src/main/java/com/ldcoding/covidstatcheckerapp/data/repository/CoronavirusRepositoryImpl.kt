package com.ldcoding.covidstatcheckerapp.data.repository

import com.ldcoding.covidstatcheckerapp.data.remote.CoronavirusApi
import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto
import com.ldcoding.covidstatcheckerapp.domain.repository.CoronavirusRepository
import javax.inject.Inject

class CoronavirusRepositoryImpl @Inject constructor(private val api: CoronavirusApi) : CoronavirusRepository {
    override suspend fun getCountries(): List<CountriesDto> {
        return api.getCountries()
    }

    override suspend fun getCountry(countryName: String): CountriesDto {
        return api.getCountryDetails(countryName)
    }
}