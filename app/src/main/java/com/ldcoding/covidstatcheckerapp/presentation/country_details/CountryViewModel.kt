package com.ldcoding.covidstatcheckerapp.presentation.country_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldcoding.covidstatcheckerapp.common.Constants
import com.ldcoding.covidstatcheckerapp.common.Resource
import com.ldcoding.covidstatcheckerapp.domain.use_cases.get_country.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CountryDetailState())
    val state: State<CountryDetailState> = _state

    init {
        stateHandle.get<String>(Constants.PARAM_COUNTRY_NAME)?.let { countryName ->
            getCountry(countryName)
        }
    }

    private fun getCountry(countryName: String) {
        getCountryUseCase(countryName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CountryDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CountryDetailState(country = result.data)
                }
                is Resource.Error -> {
                    _state.value = CountryDetailState(error = result.message ?: "Error occured")
                }
            }
        }.launchIn(viewModelScope)
    }

}