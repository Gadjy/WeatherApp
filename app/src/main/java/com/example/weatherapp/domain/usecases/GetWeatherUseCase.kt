package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.model.WeatherInfo
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(q: String): WeatherInfo {
        return repository.getWeatherByLocation(q)
    }
}
