package com.ldcoding.covidstatcheckerapp.domain.use_cases.get_country

import com.ldcoding.covidstatcheckerapp.common.Resource
import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto
import com.ldcoding.covidstatcheckerapp.data.remote.dto.toCountry
import com.ldcoding.covidstatcheckerapp.domain.model.Country
import com.ldcoding.covidstatcheckerapp.domain.model.CountryDetail
import com.ldcoding.covidstatcheckerapp.domain.repository.CoronavirusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: CoronavirusRepository
) {
    operator fun invoke(countryName: String): Flow<Resource<CountriesDto>> = flow {
        try {
            emit(Resource.Loading<CountriesDto>())
            val country = repository.getCountry(countryName)
            emit(Resource.Success<CountriesDto>(country))
        } catch (e: HttpException) {
            emit(Resource.Error<CountriesDto>(e.localizedMessage ?: "An enexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CountriesDto>(e.localizedMessage ?: "No Internet conection coudn't reach server"))
        }
    }
}