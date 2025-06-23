package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.location.DefaultLocationProvider
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.location.LocationProvider
import com.example.weatherapp.domain.repository.WeatherRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json as KotlinJson


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val BASE_URL = "https://api.weatherapi.com/v1/"
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
    @Provides
    fun provideRetrofit(json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
    @Provides
    fun provideApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db").build()

    @Provides
    fun provideDao(db: WeatherDatabase): WeatherDao = db.weatherDao()

    @Provides
    fun provideRepository(api: WeatherApi, dao: WeatherDao): WeatherRepository =
        WeatherRepositoryImpl(api, dao)



    @Provides
    fun provideLocationProvider(@ApplicationContext context: Context): LocationProvider =
        DefaultLocationProvider(context)
}


