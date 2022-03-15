package com.ldcoding.covidstatcheckerapp.presentation.countries_list

import com.ldcoding.covidstatcheckerapp.domain.model.Country

data class CountriesListState(
    val isLoading: Boolean = false,
    val countries : List<Country> = emptyList(),
    val error : String = ""
)
