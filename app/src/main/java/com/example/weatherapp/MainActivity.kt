package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.presentation.screens.WeatherScreen
import com.example.weatherapp.presentation.theme.WeatherAppTheme
import com.example.weatherapp.presentation.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                    val viewModel: WeatherViewModel = hiltViewModel()
                    WeatherScreen(viewModel)
                }
        }
    }
}

