package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
        @GET("current.json")
        suspend fun getWeather(
            @Query("key") apiKey: String,
            @Query("q") location: String // можно "Moscow" или "55.75,37.62"
        ): WeatherApiResponse
}
