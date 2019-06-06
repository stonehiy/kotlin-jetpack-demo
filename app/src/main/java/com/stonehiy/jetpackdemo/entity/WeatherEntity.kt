package com.stonehiy.jetpackdemo.entity

data class WeatherEntity(
        val `data`: List<Data>,
        val city: String,
        val cityEn: String,
        val cityid: String,
        val country: String,
        val countryEn: String,
        val update_time: String
)