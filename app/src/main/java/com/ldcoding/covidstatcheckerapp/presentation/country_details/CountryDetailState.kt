package com.ldcoding.covidstatcheckerapp.presentation.country_details

import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto

data class CountryDetailState(
    val isLoading: Boolean = false,
    val country : CountriesDto? = null,
    val error : String = ""
)
