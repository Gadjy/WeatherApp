package com.example.weatherapp.domain.location

import android.location.Location

interface LocationProvider {
    suspend fun getCurrentLocation(): Location
}
