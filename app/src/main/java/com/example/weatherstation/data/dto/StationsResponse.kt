package com.example.weatherstation.data.dto

import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("localization")
    val localization: String,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float
)