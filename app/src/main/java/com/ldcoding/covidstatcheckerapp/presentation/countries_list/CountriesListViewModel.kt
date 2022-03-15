package com.ldcoding.covidstatcheckerapp.presentation.countries_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldcoding.covidstatcheckerapp.common.Resource
import com.ldcoding.covidstatcheckerapp.domain.use_cases.get_countries.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CountriesListState())
    val state: State<CountriesListState> = _state

    init {
        getCountries()
    }
    private fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CountriesListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CountriesListState(countries = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CountriesListState(error = result.message ?: "Error occured")
                }
            }
        }.launchIn(viewModelScope)
    }

}