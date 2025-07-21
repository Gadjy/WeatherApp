package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.mappers.toDomain
import com.example.weatherapp.data.mappers.toEntity
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import java.io.IOException


private const val CACHE_DURATION_MS = 30 * 60 * 1000L
private const val API_KEY = "1d807a02e0184f94bcf223430252206"
//val apiKey = BuildConfig.WEATHER_API_KEY

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherByLocation(q: String): WeatherInfo {
        val cached = dao.getWeatherByCity(q)
        val now = System.currentTimeMillis()

        val isCacheValid = cached != null && (now - cached.timestamp < CACHE_DURATION_MS)

        return if (isCacheValid) {
            cached!!.toDomain()
        } else {
            try {
                val dto = api.getWeather(API_KEY, q)
                val domain = dto.toDomain()
                dao.insertWeather(domain.toEntity())
                domain
            } catch (e: Exception) {
                cached?.toDomain() ?: throw IOException("Ошибка загрузки и нет кэша", e)
            }
        }
    }
}
