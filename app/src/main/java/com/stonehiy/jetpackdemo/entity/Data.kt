package com.stonehiy.jetpackdemo.entity

data class Data(
        val air: Int,
        val air_level: String,
        val air_tips: String,
        val alarm: Alarm,
        val date: String,
        val day: String,
        val hours: List<Hour>,
        val humidity: Int,
        val index: List<Index>,
        val tem: String,
        val tem1: String,
        val tem2: String,
        val wea: String,
        val wea_img: String,
        val week: String,
        val win: List<String>,
        val win_speed: String
)