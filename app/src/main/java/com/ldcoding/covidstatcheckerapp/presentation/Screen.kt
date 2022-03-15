package com.ldcoding.covidstatcheckerapp.presentation

sealed class Screen(val route: String){
    object CountriesListScreen : Screen("countries")
    object CountryDetailScreen : Screen("country_detail")
    object AboutScreen : Screen("about")

}