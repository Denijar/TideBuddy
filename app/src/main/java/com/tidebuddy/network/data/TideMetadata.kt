package com.tidebuddy.network.data

data class TideMetadata(
    val latitude: String,
    val longitude: String,
    val datum: String,
    val start: String,
    val days: Int,
    val interval: Int,
    val height: String
)
