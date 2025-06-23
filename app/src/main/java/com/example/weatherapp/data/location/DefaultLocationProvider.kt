package com.example.weatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.weatherapp.domain.location.LocationProvider
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DefaultLocationProvider @Inject constructor(
    private val context: Context
) : LocationProvider {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location = suspendCancellableCoroutine { cont ->
        val client = LocationServices.getFusedLocationProviderClient(context)
        client.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    cont.resume(location)
                } else {
                    cont.resumeWithException(Exception("Не удалось получить координаты"))
                }
            }
            .addOnFailureListener {
                cont.resumeWithException(it)
            }
    }
}
