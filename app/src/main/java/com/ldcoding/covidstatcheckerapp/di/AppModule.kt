package com.ldcoding.covidstatcheckerapp.di

import com.ldcoding.covidstatcheckerapp.common.Constants
import com.ldcoding.covidstatcheckerapp.data.remote.CoronavirusApi
import com.ldcoding.covidstatcheckerapp.data.repository.CoronavirusRepositoryImpl
import com.ldcoding.covidstatcheckerapp.domain.repository.CoronavirusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoronavirusApi(): CoronavirusApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoronavirusApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoronavirusRepository(api: CoronavirusApi): CoronavirusRepository {
        return CoronavirusRepositoryImpl(api)
    }
}