package com.example.weatherapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.GetWeatherUseCase
import com.example.weatherapp.presentation.uistates.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()


    fun loadWeather(city: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val weather = getWeatherUseCase(city)
                _uiState.value = WeatherUiState.Success(weather)
            } catch (e: Exception) {
                Log.e("WeatherApp", "Ошибка при загрузке погоды: ${e.message}", e)
                _uiState.value = WeatherUiState.Error(e.message ?: "Ошибка загрузки погоды")
            }
        }
    }
}
