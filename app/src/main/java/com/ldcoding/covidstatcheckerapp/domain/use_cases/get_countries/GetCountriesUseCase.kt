package com.ldcoding.covidstatcheckerapp.domain.use_cases.get_countries

import com.ldcoding.covidstatcheckerapp.common.Resource
import com.ldcoding.covidstatcheckerapp.data.remote.dto.toCountry
import com.ldcoding.covidstatcheckerapp.domain.model.Country
import com.ldcoding.covidstatcheckerapp.domain.repository.CoronavirusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CoronavirusRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading<List<Country>>())
            var countries = repository.getCountries().map { it.toCountry() }.toMutableList()
            val world = countries[0]
            countries = countries.sortedBy { it.country } as MutableList<Country>
            countries[0] = world
            emit(Resource.Success<List<Country>>(countries))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Country>>(e.localizedMessage ?: "An enexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Country>>(e.localizedMessage ?: "No Internet conection coudn't reach server"))
        }
    }
}